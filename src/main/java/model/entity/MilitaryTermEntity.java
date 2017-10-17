package model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "militaryTerms")
public class MilitaryTermEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "end_of_military_service")
    private LocalDate endOfMilitaryService;

    @Column(name = "locality")
    private String locality;

    @Column(name = "start_of_military_service")
    private LocalDate startOfMilitaryService;

    @Column(name = "unit")
    private String unit;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veteran_uuid")
    private VeteranEntity veteran;

    public MilitaryTermEntity() {
        this(null, null, null, null, null, null);
    }

    public MilitaryTermEntity(String unit, String country, String locality, LocalDate startOfMilitaryService, LocalDate endOfMilitaryService, VeteranEntity veteran) {
        this.unit = unit;
        this.country = country;
        this.locality = locality;
        this.startOfMilitaryService = startOfMilitaryService;
        this.endOfMilitaryService = endOfMilitaryService;
        this.veteran = veteran;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof MilitaryTermEntity))
            return false;

        final MilitaryTermEntity b = (MilitaryTermEntity) object;

        return uuid != null && b.getUuid() != null && uuid.equals(b.getUuid());
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getEndOfMilitaryService() {
        return endOfMilitaryService;
    }

    public void setEndOfMilitaryService(LocalDate endOfMilitaryService) {
        this.endOfMilitaryService = endOfMilitaryService;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public LocalDate getStartOfMilitaryService() {
        return startOfMilitaryService;
    }

    public void setStartOfMilitaryService(LocalDate startOfMilitaryService) {
        this.startOfMilitaryService = startOfMilitaryService;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public VeteranEntity getVeteran() {
        return veteran;
    }

    public void setVeteran(VeteranEntity veteran) {
        setVeteran(veteran, true);
    }

    public String toString() {
        return "Срок службы: " + country + " " + locality + " " + startOfMilitaryService + " " + endOfMilitaryService;
    }

    void setVeteran(VeteranEntity veteran, boolean add) {
        this.veteran = veteran;
        if (veteran != null && add) {
            veteran.addMilitaryTerm(this, false);
        }
    }
}
