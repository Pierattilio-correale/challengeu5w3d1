package it.epicode.challengeu5w3d1.repository;

import it.epicode.challengeu5w3d1.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ViaggioRepository extends JpaRepository<Viaggio,Integer> , PagingAndSortingRepository<Viaggio,Integer> {
}
