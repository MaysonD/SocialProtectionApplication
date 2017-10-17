package database;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Map;

public class QueryBuilder {

    private String joinString;
    private String resultQuery;
    private LinkedList<String> whereArguments = new LinkedList<>();

    public void getByAddress(String value) {
        this.whereArguments.add("v.address='" + value + "'");
    }

    public void getByCaseNumber(String value) {
        this.whereArguments.add("v.case_number='" + value + "'");
    }

    public void getByCategory(String value) {
        this.joinString += " LEFT JOIN categories cat ON cat.uuid=v.category_uuid";
        this.whereArguments.add("cat.name='" + value + "'");
    }

    public void getByDateOfBirth(String value) {
        this.whereArguments.add("v.date_of_birth='" + value + "'");
    }

    public void getByDateOfDeath(String value) {
        this.whereArguments.add("v.date_of_death='" + value + "'");
    }

    public void getByDistrictCode(String value) {
        this.joinString += " LEFT JOIN districts distr ON distr.uuid=v.district_uuid";
        this.whereArguments.add("distr.code='" + value + "'");
    }

    public void getByDistrictName(String value) {
        this.joinString += " LEFT JOIN districts distr ON distr.uuid=v.district_uuid";
        this.whereArguments.add("distr.name='" + value + "'");
    }

    public void getByFirstName(String value) {
        this.whereArguments.add("v.first_name='" + value + "'");
    }

    public void getByIsDead(String value) {
        this.whereArguments.add("v.is_dead=" + value);
    }

    public void getByMarchingOrganization(String value) {
        this.whereArguments.add("v.marching_organization='" + value + "'");
    }

    public void getByMiddleName(String value) {
        this.whereArguments.add("v.middle_name='" + value + "'");
    }

    public void getByMilitaryCountry(String value) {
        if (this.isJoinNeed("mt")) {
            this.joinString += " LEFT JOIN militaryterms mt ON mt.veteran_uuid=v.uuid";
        }
        this.whereArguments.add("mt.country='" + value + "'");
    }

    public void getByMilitaryLocality(String value) {
        if (this.isJoinNeed("mt")) {
            this.joinString += " LEFT JOIN militaryterms mt ON mt.veteran_uuid=v.uuid";
        }
        this.whereArguments.add("mt.locality='" + value + "'");
    }

    public void getByMilitaryUnit(String value) {
        if (this.isJoinNeed("mt")) {
            this.joinString += " LEFT JOIN militaryterms mt ON mt.veteran_uuid=v.uuid";
        }
        this.whereArguments.add("mt.unit='" + value + "'");
    }

    public void getByPosition(String value) {
        this.whereArguments.add("v.position=" + value);
    }

    public void getByRank(String value) {
        this.joinString += " LEFT JOIN ranks rank ON rank.uuid=v.rank_uuid";
        this.whereArguments.add("rank.name='" + value + "'");
    }

    public void getByRegionalExecutiveCommittee(String value) {
        this.whereArguments.add("v.regional_executive_committee='" + value + "'");
    }

    public void getByRegistrationAddress(String value) {
        this.whereArguments.add("v.registration_address='" + value + "'");
    }

    public void getBySecondName(String value) {
        this.whereArguments.add("v.second_name='" + value + "'");
    }

    public void getBySubcategory(String value) {
        this.joinString += " LEFT JOIN subcategories sub ON sub.uuid=v.category_uuid";
        this.whereArguments.add("sub.name='" + value + "'");
    }

    public void getBySubdivision(String value) {
        this.whereArguments.add("v.subdivision='" + value + "'");
    }

    public void getByVillageExecutiveCommittee(String value) {
        this.whereArguments.add("v.village_executive_committee='" + value + "'");
    }

    public void getByWorkOrganization(String value) {
        this.joinString += " LEFT JOIN workplaces work ON work.veteran_uuid=v.uuid";
        this.whereArguments.add("work.organization='" + value + "'");
    }

    public void getByWoundType(String value) {
        this.joinString += " LEFT JOIN wounds wound ON wound.veteran_uuid=v.uuid";
        this.whereArguments.add("wound.type='" + value + "'");
    }

    public String prepareSelectQuery(Map<String, String> filters, int page) {
        this.resetQuery();
        for (Map.Entry<String, String> entry : filters.entrySet()) {
            try {
                java.lang.reflect.Method method = QueryBuilder.class.getMethod("getBy" + entry.getKey(), String.class);
                try {
                    method.invoke(this, entry.getValue());
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {

                }
            } catch (NoSuchMethodException e) {

            }
        }

        String query = "SELECT * FROM veterans v";
        if (whereArguments.size() > 0) {
            this.resultQuery = query + this.joinString + " WHERE " + String.join(" AND ", this.whereArguments);
        } else {
            this.resultQuery = query;
        }

        return this.resultQuery + " LIMIT 50 OFFSET " + (page * 50);
    }

    private boolean isJoinNeed(String alias) {
        boolean needJoin = true;
        for (String argument : this.whereArguments) {
            if (argument.substring(0, alias.length()).equals(alias)) {
                needJoin = false;
            }
        }
        return needJoin;
    }

    private void resetQuery() {
        this.whereArguments.clear();
        this.joinString = "";
        this.resultQuery = "";
    }
}
