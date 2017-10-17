package model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "districts")
public class DistrictEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid")
    private UUID uuid;

    public DistrictEntity() {
        this(null, null);
    }

    public DistrictEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof DistrictEntity))
            return false;

        final DistrictEntity b = (DistrictEntity) object;

        return uuid != null && b.getUuid() != null && uuid.equals(b.getUuid());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String toString() {
        return this.name;
    }
}
