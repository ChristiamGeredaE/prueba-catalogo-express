package com.catalogoexpress.catalog_service.Service;

import com.catalogoexpress.catalog_service.Dto.InventoryResponseDto;
import com.catalogoexpress.catalog_service.Dto.ItemResponseDto;
import com.catalogoexpress.catalog_service.models.Item;
import com.catalogoexpress.catalog_service.repository.ItemRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final RestTemplate restTemplate;
    private final Environment environment;

    public ItemService(ItemRepository itemRepository, RestTemplate restTemplate, Environment environment) {
        this.itemRepository = itemRepository;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    private ItemResponseDto mapToDTO(Item item) {
        InventoryResponseDto inventory = null;
        try {
            String inventoryServiceUrl = environment.getProperty("inventory.service.url");
            if (inventoryServiceUrl == null || inventoryServiceUrl.trim().isEmpty()) {
                inventoryServiceUrl = "http://localhost:8081/api/inventory";
            }

            inventory = restTemplate.getForObject(inventoryServiceUrl + "/" + item.getId(), InventoryResponseDto.class);
        } catch (Exception e) {
            inventory = new InventoryResponseDto();
            inventory.setStock(0);
            inventory.setStatus("OUT_OF_STOCK");
        }

        int stock = (inventory != null) ? inventory.getStock() : 0;
        String status = (inventory != null) ? inventory.getStatus() : "OUT_OF_STOCK";

        double score = (item.getRating() * Math.log(stock + 1)) / Math.max(item.getPrice(), 1.0);

        return new ItemResponseDto(
                item.getId(),
                item.getTitle(),
                item.getDescription(),
                item.getImageUrl(),
                item.getPrice(),
                item.getRating(),
                stock,
                status,
                score
        );
    }

    //Obtener todos los ítems ordenados por score de mayor a menor
    public List<ItemResponseDto> getAllItemsSortedByScore() {
        List<Item> items = itemRepository.findAll();

        return items.stream()
                .map(this::mapToDTO)
                .sorted(Comparator.comparing(ItemResponseDto::getScore).reversed())
                .collect(Collectors.toList());
    }

    //  Obtener un ítem específico por su ID
    public ItemResponseDto getItemById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado con id: " + id));

        return mapToDTO(item);
    }
}