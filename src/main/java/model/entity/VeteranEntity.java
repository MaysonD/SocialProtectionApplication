package model.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "veterans")
public class VeteranEntity {

    @Column(name = "address")
    private String address;

    @Column(name = "burial_place")
    private String burialPlace;

    @Column(name = "case_number")
    private String caseNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_uuid")
    private CategoryEntity category;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = DisplacementEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DisplacementEntity> displacements = new LinkedList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "district_uuid")
    private DistrictEntity district;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = DocumentEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<DocumentEntity> documents = new LinkedList<>();

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = FamilyMemberEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<FamilyMemberEntity> familyMembers = new LinkedList<>();

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "is_dead")
    private boolean isDead;

    @Column(name = "marching_organization")
    private String marchingOrganization;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rank_uuid")
    private RankEntity militaryRank;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = MilitaryTermEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<MilitaryTermEntity> militaryTerms = new LinkedList<>();

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "photo")
    private String photo;

    @Column(name = "position")
    private String position;

    @Column(name = "regional_executive_committee")
    private String regionalExecutiveCommittee;

    @Column(name = "registration_address")
    private String registrationAddress;

    @Column(name = "second_name")
    private String secondName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subcategory_uuid")
    private SubcategoryEntity subcategory;

    @Column(name = "subdivision")
    private String subdivision;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = VeteranHonorEntity.class, cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<VeteranHonorEntity> veteranHonors = new LinkedList<>();

    @Column(name = "village_executive_committee")
    private String villageExecutiveCommittee;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "veteran", targetEntity = WorkPlaceEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<WorkPlaceEntity> workPlaces = new LinkedList<>();

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "veteran", targetEntity = VeteranWoundEntity.class, cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<VeteranWoundEntity> wounds = new LinkedList<>();

    public void addDisplacement(DisplacementEntity displacement) {
        addDisplacement(displacement, true);
    }

    public void addDocument(DocumentEntity document) {
        addDocument(document, true);
    }

    public void addFamilyMember(FamilyMemberEntity member) {
        addFamilyMember(member, true);
    }

    public void addMilitaryTerm(MilitaryTermEntity militaryTerm) {
        addMilitaryTerm(militaryTerm, true);
    }

    public void addVeteranHonor(VeteranHonorEntity honor) {
        addVeteranHonor(honor, true);
    }

    public void addWorkPlace(WorkPlaceEntity place) {
        addWorkPlace(place, true);
    }

    public void addWound(VeteranWoundEntity wound) {
        addWound(wound, true);
    }

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if ((object == null) || !(object instanceof VeteranEntity))
            return false;

        final VeteranEntity b = (VeteranEntity) object;

        return uuid != null && b.getUuid() != null && uuid.equals(b.getUuid());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBurialPlace() {
        return burialPlace;
    }

    public void setBurialPlace(String burialPlace) {
        this.burialPlace = burialPlace;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public List<DisplacementEntity> getDisplacements() {
        return displacements;
    }

    public void setDisplacements(List<DisplacementEntity> displacements) {
        this.displacements = displacements;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public List<DocumentEntity> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentEntity> documents) {
        this.documents = documents;
    }

    public List<FamilyMemberEntity> getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(List<FamilyMemberEntity> familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMarchingOrganization() {
        return marchingOrganization;
    }

    public void setMarchingOrganization(String marchingOrganization) {
        this.marchingOrganization = marchingOrganization;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public RankEntity getMilitaryRank() {
        return militaryRank;
    }

    public void setMilitaryRank(RankEntity rank) {
        this.militaryRank = rank;
    }

    public List<MilitaryTermEntity> getMilitaryTerms() {
        return militaryTerms;
    }

    public void setMilitaryTerms(List<MilitaryTermEntity> militaryTerms) {
        this.militaryTerms = militaryTerms;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRegionalExecutiveCommittee() {
        return regionalExecutiveCommittee;
    }

    public void setRegionalExecutiveCommittee(String regionalExecutiveCommittee) {
        this.regionalExecutiveCommittee = regionalExecutiveCommittee;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public SubcategoryEntity getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubcategoryEntity subcategory) {
        this.subcategory = subcategory;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<VeteranHonorEntity> getVeteranHonors() {
        return veteranHonors;
    }

    public void setVeteranHonors(List<VeteranHonorEntity> veteranHonor) {
        this.veteranHonors = veteranHonor;
    }

    public String getVillageExecutiveCommittee() {
        return villageExecutiveCommittee;
    }

    public void setVillageExecutiveCommittee(String villageExecutiveCommittee) {
        this.villageExecutiveCommittee = villageExecutiveCommittee;
    }

    public List<WorkPlaceEntity> getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(List<WorkPlaceEntity> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public List<VeteranWoundEntity> getWounds() {
        return wounds;
    }

    public void setWounds(List<VeteranWoundEntity> wounds) {
        this.wounds = wounds;
    }

    public boolean isDead() {
        return isDead;
    }

    public void removeDisplacement(DisplacementEntity displacement) {
        getDisplacements().remove(displacement);
        displacement.setVeteran(null);
    }

    public void removeDocument(DocumentEntity document) {
        getDocuments().remove(document);
        document.setVeteran(null);
    }

    public void removeFamilyMember(FamilyMemberEntity member) {
        getFamilyMembers().remove(member);
        member.setVeteran(null);

    }

    public void removeMilitaryTerm(MilitaryTermEntity militaryTerm) {
        getMilitaryTerms().remove(militaryTerm);
        militaryTerm.setVeteran(null);

    }

    public void removeVeteranHonor(VeteranHonorEntity honor) {
        getVeteranHonors().remove(honor);
        honor.setVeteran(null);

    }

    public void removeWorkPlace(WorkPlaceEntity place) {
        getWorkPlaces().remove(place);
        place.setVeteran(null);

    }

    public void removeWound(VeteranWoundEntity wound) {
        getWounds().remove(wound);
        wound.setVeteran(null);

    }

    public void setIsDead(boolean dead) {
        isDead = dead;
    }

    @Override
    public String toString() {
        return "VeteranEntity{" +
                "uuid=" + uuid +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", district=" + district +
                ", middleName='" + middleName + '\'' +
                ", caseNumber='" + caseNumber + '\'' +
                ", subdivision='" + subdivision + '\'' +
                ", address='" + address + '\'' +
                ", registrationAddress='" + registrationAddress + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", dateOfDeath=" + dateOfDeath +
                ", regionalExecutiveCommittee='" + regionalExecutiveCommittee + '\'' +
                ", villageExecutiveCommittee='" + villageExecutiveCommittee + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", marchingOrganization='" + marchingOrganization + '\'' +
                ", burialPlace='" + burialPlace + '\'' +
                ", isDead=" + isDead +
                ", position='" + position + '\'' +
                ", rank=" + militaryRank +
                ", category=" + category +
                ", subcategory=" + subcategory +
                ", familyMembers=" + familyMembers +
                ", workPlaces=" + workPlaces +
                ", wounds=" + wounds +
                ", documents=" + documents +
                ", displacements=" + displacements +
                ", militaryTerms=" + militaryTerms +
                ", veteranHonors=" + veteranHonors +
                '}';
    }

    void addFamilyMember(FamilyMemberEntity member, boolean set) {
        if (member != null) {
            List<FamilyMemberEntity> members = this.getFamilyMembers();
            if (members.contains(member)) {
                members.remove(member);
                members.add(member);
            } else {
                members.add(member);
            }
            if (set) {
                member.setVeteran(this, false);
            }
        }
    }

    void addWorkPlace(WorkPlaceEntity place, boolean set) {
        if (place != null) {
            List<WorkPlaceEntity> places = this.getWorkPlaces();
            if (places.contains(place)) {
                places.remove(place);
                places.add(place);
            } else {
                places.add(place);
            }
            if (set) {
                place.setVeteran(this, false);
            }
        }
    }

    void addWound(VeteranWoundEntity wound, boolean set) {
        if (wound != null) {
            List<VeteranWoundEntity> wounds = this.getWounds();
            if (wounds.contains(wound)) {
                wounds.remove(wound);
                wounds.add(wound);
            } else {
                wounds.add(wound);
            }
            if (set) {
                wound.setVeteran(this, false);
            }
        }
    }

    void addDocument(DocumentEntity document, boolean set) {
        if (document != null) {
            List<DocumentEntity> documents = this.getDocuments();
            if (documents.contains(document)) {
                documents.remove(document);
                documents.add(document);
            } else {
                documents.add(document);
            }
            if (set) {
                document.setVeteran(this, false);
            }
        }
    }

    void addDisplacement(DisplacementEntity displacement, boolean set) {
        if (displacement != null) {
            List<DisplacementEntity> displacements = this.getDisplacements();
            if (displacements.contains(displacement)) {
                displacements.remove(displacement);
                displacements.add(displacement);
            } else {
                displacements.add(displacement);
            }
            if (set) {
                displacement.setVeteran(this, false);
            }
        }
    }

    void addMilitaryTerm(MilitaryTermEntity militaryTerm, boolean set) {
        if (militaryTerm != null) {
            List<MilitaryTermEntity> militaryTerms = this.getMilitaryTerms();
            if (militaryTerms.contains(militaryTerm)) {
                militaryTerms.remove(militaryTerm);
                militaryTerms.add(militaryTerm);
            } else {
                militaryTerms.add(militaryTerm);
            }
            if (set) {
                militaryTerm.setVeteran(this, false);
            }
        }
    }

    void addVeteranHonor(VeteranHonorEntity honor, boolean set) {
        if (honor != null) {
            List<VeteranHonorEntity> veteranHonors = this.getVeteranHonors();
            if (veteranHonors.contains(honor)) {
                veteranHonors.remove(honor);
                veteranHonors.add(honor);
            } else {
                veteranHonors.add(honor);
            }
            if (set) {
                honor.setVeteran(this, false);
            }
        }
    }
}
