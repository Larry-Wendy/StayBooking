package com.laioffer.staybooking.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stay_reserved_date")
public class StayReservedDate implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StayReservedDateKey id;

    // column stay_id also is a foreign key of table stay
    @MapsId("stay_id") // 和PK中定义的相同
    @ManyToOne
    private Stay stay;

    public StayReservedDate() {} // hibernate为什么需要一个空的constructor? 先创建一个空的对象，再利用reflection再set
                                 // 什么是java reflection? Annotation 就是通过reflection实现

    public StayReservedDate(StayReservedDateKey id, Stay stay) {
        this.id = id;
        this.stay = stay;
    }

    public StayReservedDateKey getId() {
        return id;
    }

    public Stay getStay() {
        return stay;
    }
}
