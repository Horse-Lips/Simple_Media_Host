package org.simple.project.simple_media_host.ImageHost;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<ImageEntity, Long> { }