package com.mysocialmedia.firebase.service.repositories;

import com.mysocialmedia.firebase.service.models.entities.Imagenes;
import org.springframework.data.repository.CrudRepository;

public interface ImagenRespository extends CrudRepository<Imagenes, Long> {
}
