package com.laioffer.staybooking.repository;

import com.laioffer.staybooking.model.Stay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.laioffer.staybooking.model.User;
import java.util.List;

@Repository
public interface StayRepository extends JpaRepository<Stay, Long> { // why extend JPA? 是接口，自动生成，自动实现一些基本方法
                                          //model对应的table, Pk的type
    List<Stay> findByHost(User user); //命名符合规范就可以自动实现，default (public/private...满足要求可以省略)

    Stay findByIdAndHost(Long id, User host);

    List<Stay> findByIdInAndGuestNumberGreaterThanEqual(List<Long> ids, int guestNumber);
}
