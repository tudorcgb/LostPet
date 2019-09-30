package com.lostpet.backend.repository;


import com.lostpet.backend.entity.Listing;
import com.lostpet.backend.entity.User;
import com.lostpet.backend.entity.Category;
import com.tudor.dto.SearchParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByWriter(User writer);

    List<Listing> findByWriterId(Long writer);

    @Query("SELECT list FROM Listing list WHERE (list.breed.id = :breed OR :breed IS NULL) "
            + "AND list.pierdut = :pierdut "
            + "AND title LIKE CONCAT('%',:searchString,'%')"
            + "AND (list.lat >= :startLat OR :startLat IS NULL)"
            + "AND (list.lng >= :startLng OR :startLng IS NULL)"
            + "AND (list.lat_end <= :endLat OR :endLat IS NULL)"
            + "AND (list.lng_end <= :endLng OR :endLng IS NULL)")
//            + "ORDER BY e.salary DESC")
        List<Listing> findBySearchParams(@Param("breed") Long breed, @Param("pierdut") boolean pierdut,@Param("searchString") String search,
            @Param("startLat") String startLat,@Param("startLng") String startLng,@Param("endLat") String endLat,@Param("endLng") String endLng);

//    @Query("SELECT list FROM Listing list WHERE "
//            + " list.pierdut = :pierdut")
////            + "WHERE e.salary < e2.salary AND e2.dept = :dept) < :topSalNum "
////            + "ORDER BY e.salary DESC")
//    List<Listing> findBySearchParams( @Param("pierdut") boolean pierdut);
    //List<Listing> findByCategories(List<Category> categories);



    //Listing save(Listing article);
}
