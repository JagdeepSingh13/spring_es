package com.example.restrO.repositories;

import com.example.restrO.domain.entities.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepo extends ElasticsearchRepository<Restaurant, String> {



}
