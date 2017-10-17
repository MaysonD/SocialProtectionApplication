package model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "veteranHonors")
public class VeteranHonorEntity {

    @Column(name = "date_of_receiving", columnDefinition = "DATE")
    private LocalDate dateOfReceiving;

    @Column(name = "decree")
    private String decree;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne()
    @JoinColumn(name = "honor_uuid")
    private HonorEntity honor;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid")
    private UUID uuid;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToOne()
    @JoinColumn(name = "veteran_uuid")
    private VeteranEntity veteran;

    public VeteranHonorEntity() {
        this(null, null, null, null);
    }

    public VeteranHonorEntity(LocalDate dateOfReceiving, String decree, VeteranEntity veteran, HonorEntity honor) {
        this.dateOfReceiving = dateOfReceiving;
        this.decree = decree;
        this.honor = honor;
        this.veteran = veteran;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof VeteranHonorEntity))
            return false;

        final VeteranHonorEntity b = (VeteranHonorEntity) object;

        return uuid != null && b.getUuid() != null && uuid.equals(b.getUuid());
    }

    public LocalDate getDateOfReceiving() {
        return dateOfReceiving;
    }

    public void setDateOfReceiving(LocalDate dateOfRecieving) {
        this.dateOfReceiving = dateOfRecieving;
    }

    public String getDecree() {
        return decree;
    }

    public void setDecree(String order) {
        this.decree = order;
    }

    public HonorEntity getHonor() {
        return honor;
    }

    public void setHonor(HonorEntity honor) {
        this.honor = honor;
    }

    public UUID getUuid() {
        return uuid;
    }

    public VeteranEntity getVeteran() {
        return veteran;
    }

    public void setVeteran(VeteranEntity veteran) {
        setVeteran(veteran, true);
    }

    public String toString() {
        return "Награда: " + dateOfReceiving + " " + decree;
    }

    void setVeteran(VeteranEntity veteran, boolean add) {
        this.veteran = veteran;
        if (veteran != null && add) {
            veteran.addVeteranHonor(this, false);
        }
    }
}
