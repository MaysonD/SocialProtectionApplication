package model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.UUID;

@XmlRootElement
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "issued_by")
    private String issuedBy;

    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private String number;

    @Column(name = "series")
    private String series;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veteran_uuid")
    private VeteranEntity veteran;

    public DocumentEntity() {
        this(null, null, null, null, null, null);
    }

    public DocumentEntity(String name, String number, String series, String issuedBy, LocalDate date, VeteranEntity veteran) {
        this.name = name;
        this.number = number;
        this.series = series;
        this.issuedBy = issuedBy;
        this.date = date;
        this.veteran = veteran;
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof DocumentEntity))
            return false;

        final DocumentEntity b = (DocumentEntity) object;

        return uuid != null && b.getUuid() != null && uuid.equals(b.getUuid());
    }

    public LocalDate getDate() {
        return date;
    }
    @XmlElement
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getIssuedBy() {
        return issuedBy;
    }
    @XmlElement
    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    @XmlElement
    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeries() {
        return series;
    }
    @XmlElement
    public void setSeries(String series) {
        this.series = series;
    }

    public UUID getUuid() {
        return uuid;
    }
    @XmlElement
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public VeteranEntity getVeteran() {
        return veteran;
    }
    @XmlElement
    public void setVeteran(VeteranEntity veteran) {
        setVeteran(veteran, true);
    }

    public String toString() {
        return "Документ: " + name + " " + number + " " + series + " " + date + " " + issuedBy;
    }

    void setVeteran(VeteranEntity veteran, boolean add) {
        this.veteran = veteran;
        if (veteran != null && add) {
            veteran.addDocument(this, false);
        }
    }
}
