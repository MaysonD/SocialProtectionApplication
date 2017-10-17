package helpers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import model.entity.VeteranEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ExcelTableGenerator { //TODO remake this hell(3 course students are best)

    private static Workbook book = new HSSFWorkbook();
    private static Sheet sheet = book.createSheet("Ветераны");

    public static void writeIntoExcel(LinkedList<VeteranEntity> selectedPersons, File file) throws IOException {
        createFirstRow();
        int max = 1;
        for (VeteranEntity selectedPerson : selectedPersons) {
            max += createMainInfo(selectedPerson, max);
        }

        FileOutputStream fileStream = new FileOutputStream(file);
        book.write(fileStream);
        book.close();
        fileStream.close();
    }

    private static void createFirstRow() {
        Row tableRow = sheet.createRow(0);
        Cell lastName = tableRow.createCell(0);
        lastName.setCellValue("Фамилия");

        Cell firstName = tableRow.createCell(1);
        firstName.setCellValue("Имя");

        Cell middleName = tableRow.createCell(2);
        middleName.setCellValue("Отчество");

        Cell rank = tableRow.createCell(3);
        rank.setCellValue("Воинское звание");

        Cell position = tableRow.createCell(4);
        position.setCellValue("Должность");

        Cell district = tableRow.createCell(5);
        district.setCellValue("Район");

        Cell code = tableRow.createCell(6);
        code.setCellValue("Код района");

        Cell category = tableRow.createCell(7);
        category.setCellValue("Категория");

        Cell subcategory = tableRow.createCell(8);
        subcategory.setCellValue("Подкатегория");

        Cell caseNumber = tableRow.createCell(9);
        caseNumber.setCellValue("Номер дела");

        Cell dateOfBirth = tableRow.createCell(10);
        dateOfBirth.setCellValue("Дата рождения");

        Cell subvision = tableRow.createCell(11);
        subvision.setCellValue("Подразделение");

        Cell villigeExecutiveCommitete = tableRow.createCell(12);
        villigeExecutiveCommitete.setCellValue("Деревенский исполнительный комитет");

        Cell addres = tableRow.createCell(13);
        addres.setCellValue("Адрес");

        Cell regestrationAddres = tableRow.createCell(14);
        regestrationAddres.setCellValue("Регестрация");

        Cell phoneNumber = tableRow.createCell(15);
        phoneNumber.setCellValue("Номер телефона");

        Cell marchingOrganisation = tableRow.createCell(16);
        marchingOrganisation.setCellValue("Организация");

        Cell isDead = tableRow.createCell(17);
        isDead.setCellValue("Статус");

        Cell burialPlace = tableRow.createCell(18);
        burialPlace.setCellValue("Место захоронения");

        Cell burialDate = tableRow.createCell(19);
        burialDate.setCellValue("Дата смерти");

        Cell arrivedDate = tableRow.createCell(20);
        arrivedDate.setCellValue("Дата прибытия");

        Cell arrivedPlace = tableRow.createCell(21);
        arrivedPlace.setCellValue("Место прибытия");

        Cell decreasedDate = tableRow.createCell(22);
        decreasedDate.setCellValue("Дата отбытия");

        Cell dercreasedPlace = tableRow.createCell(23);
        dercreasedPlace.setCellValue("Место отбытия");

        Cell documentName = tableRow.createCell(24);
        documentName.setCellValue("Название документа");

        Cell dateOfDocumnet = tableRow.createCell(25);
        dateOfDocumnet.setCellValue("Дата выдачи");

        Cell numberOfDoc = tableRow.createCell(26);
        numberOfDoc.setCellValue("Номер паспорта");

        Cell seriesOfDoc = tableRow.createCell(27);
        seriesOfDoc.setCellValue("Серия паспорта");

        Cell issued_by = tableRow.createCell(28);
        issued_by.setCellValue("Выдано");

        Cell relationDegree = tableRow.createCell(29);
        relationDegree.setCellValue("Степень родства");

        Cell fullName = tableRow.createCell(30);
        fullName.setCellValue("Полное имя");

        Cell addresOfRelative = tableRow.createCell(31);
        addresOfRelative.setCellValue("Адрес");

        Cell phoneNumberOfRelative = tableRow.createCell(32);
        phoneNumberOfRelative.setCellValue("Номер телефона");

        Cell birthDateOfRelative = tableRow.createCell(33);
        birthDateOfRelative.setCellValue("Дата рождения");

        Cell honors = tableRow.createCell(34);
        honors.setCellValue("Награды");

        Cell dateOfRecieving = tableRow.createCell(35);
        dateOfRecieving.setCellValue("Дата получения награды");

        Cell honorDecree = tableRow.createCell(36);
        honorDecree.setCellValue("Приказ");

        Cell unit = tableRow.createCell(37);
        unit.setCellValue("Подразделение");

        Cell country = tableRow.createCell(38);
        country.setCellValue("Страна");

        Cell locality = tableRow.createCell(39);
        locality.setCellValue("Место службы");

        Cell startOfMilitaryService = tableRow.createCell(40);
        startOfMilitaryService.setCellValue("Начало службы");

        Cell endOfMilitaryService = tableRow.createCell(41);
        endOfMilitaryService.setCellValue("Окончание службы");

        Cell organisation = tableRow.createCell(42);
        organisation.setCellValue("Место работы");

        Cell locationOfOrganisation = tableRow.createCell(43);
        locationOfOrganisation.setCellValue("Расположение");

        Cell positionInOrganisation = tableRow.createCell(44);
        positionInOrganisation.setCellValue("Должность");

        Cell typeOfWound = tableRow.createCell(45);
        typeOfWound.setCellValue("Ранение");

        Cell dateOfWound = tableRow.createCell(46);
        dateOfWound.setCellValue("Дата получения ранения");

        Cell disabilityOfWound = tableRow.createCell(47);
        disabilityOfWound.setCellValue("Тяжесть ранения");

    }

    private static int createMainInfo(VeteranEntity selectedPerson, int startingRow) throws NullPointerException {
        int[] findMax = {
                selectedPerson.getVeteranHonors().size(),
                selectedPerson.getMilitaryTerms().size(),
                selectedPerson.getDisplacements().size(),
                selectedPerson.getFamilyMembers().size(),
                selectedPerson.getDocuments().size(),
                selectedPerson.getWorkPlaces().size(),
                selectedPerson.getWounds().size()
        };
        int tableRowsNumber = 0;
        for (int i = 0; i < findMax.length; i++) {
            if (findMax[tableRowsNumber] < findMax[i]) {
                tableRowsNumber = i;
            }
        }
        tableRowsNumber = findMax[tableRowsNumber];
        if (tableRowsNumber == 0) {
            tableRowsNumber = 1;
        }
        Row[] tableRows = new Row[tableRowsNumber];
        for (int i = 0; i < tableRowsNumber; i++) {
            tableRows[i] = sheet.createRow(startingRow + i);
        }
        // Фамиля
        Cell lastName = tableRows[0].createCell(0);
        if (selectedPerson.getSecondName() != null) {
            lastName.setCellValue(selectedPerson.getSecondName());
        } else lastName.setCellValue("");
        //Имя
        Cell firstName = tableRows[0].createCell(1);
        if (selectedPerson.getFirstName() != null) {
            firstName.setCellValue(selectedPerson.getFirstName());
        } else firstName.setCellValue("");
        //Отчетсво
        Cell middleName = tableRows[0].createCell(2);
        if (selectedPerson.getMiddleName() != null) {
            middleName.setCellValue(selectedPerson.getMiddleName());
        } else middleName.setCellValue("");
        //Ранк
        Cell rank = tableRows[0].createCell(3);
        if (selectedPerson.getMilitaryRank() != null) {
            if (selectedPerson.getMilitaryRank().getName() != null) {
                rank.setCellValue(selectedPerson.getMilitaryRank().getName());
            } else rank.setCellValue("");
        } else rank.setCellValue("");
        //Должность
        Cell position = tableRows[0].createCell(4);
        if (selectedPerson.getPosition() != null) {
            position.setCellValue(selectedPerson.getPosition());
        } else position.setCellValue("");
        //Район
        Cell district = tableRows[0].createCell(5);
        if (selectedPerson.getDistrict() != null) {
            if (selectedPerson.getDistrict().getName() != null) {
                district.setCellValue(selectedPerson.getDistrict().getName());
            } else district.setCellValue("");
        } else district.setCellValue("");
        //Код района
        Cell code = tableRows[0].createCell(6);
        if (selectedPerson.getDistrict() != null) {
            if (selectedPerson.getDistrict().getCode() != null) {
                code.setCellValue(selectedPerson.getDistrict().getCode());
            } else code.setCellValue("");
        } else code.setCellValue("");
        //Категория
        Cell category = tableRows[0].createCell(7);
        if (selectedPerson.getCategory() != null) {
            if (selectedPerson.getCategory().getName() != null) {
                category.setCellValue(selectedPerson.getCategory().getName());
            } else category.setCellValue("");
        } else category.setCellValue("");
        //Подкатегория
        Cell subcategory = tableRows[0].createCell(8);
        if (selectedPerson.getSubcategory() != null) {
            if (selectedPerson.getSubcategory().getName() != null) {
                subcategory.setCellValue(selectedPerson.getSubcategory().getName());
            } else subcategory.setCellValue("");
        } else subcategory.setCellValue("");
        //Номер дела
        Cell case_number = tableRows[0].createCell(9);
        if (selectedPerson.getCaseNumber() != null) {
            case_number.setCellValue(selectedPerson.getCaseNumber());
        } else case_number.setCellValue("");
        //Дата рождения
        Cell dateofbirth = tableRows[0].createCell(10);
        if (selectedPerson.getDateOfBirth() != null) {
            dateofbirth.setCellValue(selectedPerson.getDateOfBirth().toString());
        } else dateofbirth.setCellValue("");
        //Подразделение
        Cell subvision = tableRows[0].createCell(11);
        if (selectedPerson.getSubdivision() != null) {
            subvision.setCellValue(selectedPerson.getSubdivision());
        } else subvision.setCellValue("");
        //Деревенский исполнительный комитет
        Cell villigeExecutiveComittee = tableRows[0].createCell(12);
        if (selectedPerson.getVillageExecutiveCommittee() != null) {
            villigeExecutiveComittee.setCellValue(selectedPerson.getVillageExecutiveCommittee());
        } else villigeExecutiveComittee.setCellValue("");
        //Адрес
        Cell addres = tableRows[0].createCell(13);
        if (selectedPerson.getAddress() != null) {
            addres.setCellValue(selectedPerson.getAddress());
        } else addres.setCellValue("");
        //Адрес регестрации
        Cell regestrationAddres = tableRows[0].createCell(14);
        if (selectedPerson.getRegistrationAddress() != null) {
            regestrationAddres.setCellValue(selectedPerson.getRegistrationAddress());
        } else regestrationAddres.setCellValue("");
        //Номер телефона
        Cell phoneNumber = tableRows[0].createCell(15);
        if (selectedPerson.getPhoneNumber() != null) {
            phoneNumber.setCellValue(selectedPerson.getPhoneNumber());
        } else phoneNumber.setCellValue("");
        //Организация
        Cell marchingOrganisation = tableRows[0].createCell(16);
        if (selectedPerson.getMarchingOrganization() != null) {
            marchingOrganisation.setCellValue(selectedPerson.getMarchingOrganization());
        } else marchingOrganisation.setCellValue(" ");
        //Статус
        Cell isDead = tableRows[0].createCell(17);
        if (selectedPerson.isDead()) {
            isDead.setCellValue("Мертв");
        } else isDead.setCellValue("Жив");
        //Место захоранения
        Cell burialPlace = tableRows[0].createCell(18);
        if (selectedPerson.getBurialPlace() != null) {
            burialPlace.setCellValue(selectedPerson.getBurialPlace());
        } else burialPlace.setCellValue("");
        //Дата захоранения
        Cell burialDate = tableRows[0].createCell(19);
        if (selectedPerson.getDateOfDeath() != null) {
            burialDate.setCellValue(selectedPerson.getDateOfDeath().toString());
        } else burialDate.setCellValue("");


        Cell arrivedDate = tableRows[0].createCell(20);
        Cell arrivedPlace = tableRows[0].createCell(21);
        Cell decreasedDate = tableRows[0].createCell(22);
        Cell dercreasedPlace = tableRows[0].createCell(23);
        if (selectedPerson.getDisplacements() != null) {
            //Дата прибытия
            if (selectedPerson.getDisplacements().size() > 0) {
                for (int i = 0; i < selectedPerson.getDisplacements().size(); i++) {
                    if (i != 0) arrivedDate = tableRows[i].createCell(20);
                    if (selectedPerson.getDisplacements().get(i).getArrivedDate() != null) {
                        arrivedDate.setCellValue(selectedPerson.getDisplacements().get(i).getArrivedDate().toString());
                    } else arrivedDate.setCellValue("");
                }
            } else arrivedDate.setCellValue("");
            //Место прибытия
            if (selectedPerson.getDisplacements().size() > 0) {
                for (int i = 0; i < selectedPerson.getDisplacements().size(); i++) {
                    if (i != 0) arrivedPlace = tableRows[i].createCell(21);
                    if (selectedPerson.getDisplacements().get(i).getArrivedPlace() != null) {
                        arrivedPlace.setCellValue(selectedPerson.getDisplacements().get(i).getArrivedPlace());
                    } else arrivedPlace.setCellValue("");
                }
            } else arrivedPlace.setCellValue("");
            //Дата отбытия
            if (selectedPerson.getDisplacements().size() > 0) {
                for (int i = 0; i < selectedPerson.getDisplacements().size(); i++) {
                    if (i != 0) decreasedDate = tableRows[i].createCell(22);
                    if (selectedPerson.getDisplacements().get(i).getDecreasedDate() != null) {
                        decreasedDate.setCellValue(selectedPerson.getDisplacements().get(i).getDecreasedDate().toString());
                    } else decreasedDate.setCellValue("");
                }
            } else decreasedDate.setCellValue("");
            //Место отбытия
            if (selectedPerson.getDisplacements().size() > 0) {
                for (int i = 0; i < selectedPerson.getDisplacements().size(); i++) {
                    if (i != 0) dercreasedPlace = tableRows[i].createCell(23);
                    if (selectedPerson.getDisplacements().get(i).getDecreasedPlace() != null) {
                        dercreasedPlace.setCellValue(selectedPerson.getDisplacements().get(i).getDecreasedPlace());
                    } else dercreasedPlace.setCellValue("");
                }
            } else dercreasedPlace.setCellValue("");
        } else {
            arrivedDate.setCellValue("");
            arrivedPlace.setCellValue("");
            decreasedDate.setCellValue("");
            dercreasedPlace.setCellValue("");

        }


        Cell documentName = tableRows[0].createCell(24);
        Cell dateOfDocument = tableRows[0].createCell(25);
        Cell numberOfDocument = tableRows[0].createCell(26);
        Cell seriesOfDocument = tableRows[0].createCell(27);
        Cell issued_by = tableRows[0].createCell(28);
        if (selectedPerson.getDocuments() != null) {
            //Название документа
            if (selectedPerson.getDocuments().size() > 0) {
                for (int i = 0; i < selectedPerson.getDocuments().size(); i++) {
                    if (i != 0) documentName = tableRows[i].createCell(24);
                    if (selectedPerson.getDocuments().get(i).getName() != null) {
                        documentName.setCellValue(selectedPerson.getDocuments().get(i).getName());
                    } else documentName.setCellValue("");
                }
            } else documentName.setCellValue("");
            //Дата выдачи
            if (selectedPerson.getDocuments().size() > 0) {
                for (int i = 0; i < selectedPerson.getDocuments().size(); i++) {
                    if (i != 0) dateOfDocument = tableRows[i].createCell(25);
                    if (selectedPerson.getDocuments().get(i).getDate() != null) {
                        dateOfDocument.setCellValue(selectedPerson.getDocuments().get(i).getDate().toString());
                    } else dateOfDocument.setCellValue("");
                }
            } else dateOfDocument.setCellValue("");
            //Номер
            if (selectedPerson.getDocuments().size() > 0) {
                for (int i = 0; i < selectedPerson.getDocuments().size(); i++) {
                    if (i != 0) numberOfDocument = tableRows[i].createCell(26);
                    if (selectedPerson.getDocuments().get(i).getNumber() != null) {
                        numberOfDocument.setCellValue(selectedPerson.getDocuments().get(i).getNumber());
                    } else numberOfDocument.setCellValue("");
                }
            } else numberOfDocument.setCellValue("");
            //Серия
            if (selectedPerson.getDocuments().size() > 0) {
                for (int i = 0; i < selectedPerson.getDocuments().size(); i++) {
                    if (i != 0) seriesOfDocument = tableRows[i].createCell(27);
                    if (selectedPerson.getDocuments().get(i).getSeries() != null) {
                        seriesOfDocument.setCellValue(selectedPerson.getDocuments().get(i).getSeries());
                    } else seriesOfDocument.setCellValue("");
                }
            } else seriesOfDocument.setCellValue("");
            //Кем выдано
            if (selectedPerson.getDocuments().size() > 0) {
                for (int i = 0; i < selectedPerson.getDocuments().size(); i++) {
                    if (i != 0) issued_by = tableRows[i].createCell(28);
                    if (selectedPerson.getDocuments().get(i).getIssuedBy() != null) {
                        issued_by.setCellValue(selectedPerson.getDocuments().get(i).getIssuedBy());
                    } else issued_by.setCellValue("");
                }
            } else issued_by.setCellValue("");
        } else {
            documentName.setCellValue("");
            dateOfDocument.setCellValue("");
            numberOfDocument.setCellValue("");
            seriesOfDocument.setCellValue("");
            issued_by.setCellValue("");
        }


        Cell relationDegree = tableRows[0].createCell(29);
        Cell fullName = tableRows[0].createCell(30);
        Cell addresOfRelative = tableRows[0].createCell(31);
        Cell phoneNumberOfRelative = tableRows[0].createCell(32);
        Cell birthDateOfRelative = tableRows[0].createCell(33);
        if (selectedPerson.getFamilyMembers() != null) {
            //Степень родства
            if (selectedPerson.getFamilyMembers().size() > 0) {
                for (int i = 0; i < selectedPerson.getFamilyMembers().size(); i++) {
                    if (i != 0) relationDegree = tableRows[i].createCell(29);
                    if (selectedPerson.getFamilyMembers().get(i).getRelationDegree() != null) {
                        relationDegree.setCellValue(selectedPerson.getFamilyMembers().get(i).getRelationDegree());
                    } else relationDegree.setCellValue("");
                }
            } else relationDegree.setCellValue("");
            //Полное имя
            if (selectedPerson.getFamilyMembers().size() > 0) {
                for (int i = 0; i < selectedPerson.getFamilyMembers().size(); i++) {
                    if (i != 0) fullName = tableRows[i].createCell(30);
                    if (selectedPerson.getFamilyMembers().get(i).getFullName() != null) {
                        fullName.setCellValue(selectedPerson.getFamilyMembers().get(i).getFullName());
                    } else fullName.setCellValue("");
                }
            } else fullName.setCellValue("");
            //Адрес прлживания родствиника
            if (selectedPerson.getFamilyMembers().size() > 0) {
                for (int i = 0; i < selectedPerson.getFamilyMembers().size(); i++) {
                    if (i != 0) addresOfRelative = tableRows[i].createCell(31);
                    if (selectedPerson.getFamilyMembers().get(i).getAddress() != null) {
                        addresOfRelative.setCellValue(selectedPerson.getFamilyMembers().get(i).getAddress());
                    } else addresOfRelative.setCellValue("");
                }
            } else addresOfRelative.setCellValue("");
            //Номер телефона
            if (selectedPerson.getFamilyMembers().size() > 0) {
                for (int i = 0; i < selectedPerson.getFamilyMembers().size(); i++) {
                    if (i != 0) phoneNumberOfRelative = tableRows[i].createCell(32);
                    if (selectedPerson.getFamilyMembers().get(i).getPhoneNumber() != null) {
                        phoneNumberOfRelative.setCellValue(selectedPerson.getFamilyMembers().get(i).getPhoneNumber());
                    } else phoneNumberOfRelative.setCellValue(" ");
                }
            } else phoneNumberOfRelative.setCellValue("");
            //Дата рождения
            if (selectedPerson.getFamilyMembers().size() > 0) {
                for (int i = 0; i < selectedPerson.getFamilyMembers().size(); i++) {
                    if (i != 0) birthDateOfRelative = tableRows[i].createCell(33);
                    if (selectedPerson.getFamilyMembers().get(i).getDateOfBirth() != null) {
                        birthDateOfRelative.setCellValue(selectedPerson.getFamilyMembers().get(i).getDateOfBirth().toString());
                    } else birthDateOfRelative.setCellValue("");
                }
            } else birthDateOfRelative.setCellValue("");
        } else {
            relationDegree.setCellValue("");
            fullName.setCellValue("");
            addresOfRelative.setCellValue("");
            phoneNumber.setCellValue("");
            birthDateOfRelative.setCellValue("");
        }


        Cell honors = tableRows[0].createCell(34);
        Cell honorsDate = tableRows[0].createCell(35);
        Cell honorsDecree = tableRows[0].createCell(36);
        if (selectedPerson.getVeteranHonors() != null) {
            //Награды
            if (selectedPerson.getVeteranHonors().size() != 0) {
                for (int i = 0; i < selectedPerson.getVeteranHonors().size(); i++) {
                    if (i != 0) honors = tableRows[i].createCell(34);
                    if (selectedPerson.getVeteranHonors().get(i).getHonor() != null) {
                        honors.setCellValue(selectedPerson.getVeteranHonors().get(i).getHonor().getName());
                    } else honors.setCellValue("");
                }
            } else honors.setCellValue("");
            //Дата получения награды
            if (selectedPerson.getVeteranHonors().size() != 0) {
                for (int i = 0; i < selectedPerson.getVeteranHonors().size(); i++) {
                    if (i != 0) honorsDate = tableRows[i].createCell(35);
                    if (selectedPerson.getVeteranHonors().get(i).getDateOfReceiving() != null) {
                        honorsDate.setCellValue(selectedPerson.getVeteranHonors().get(i).getDateOfReceiving().toString());
                    } else honorsDate.setCellValue("");
                }
            } else honorsDate.setCellValue("");
            //Приказ
            if (selectedPerson.getVeteranHonors().size() != 0) {
                for (int i = 0; i < selectedPerson.getVeteranHonors().size(); i++) {
                    if (i != 0) honorsDecree = tableRows[i].createCell(36);
                    if (selectedPerson.getVeteranHonors().get(i).getDecree() != null) {
                        honorsDecree.setCellValue(selectedPerson.getVeteranHonors().get(i).getDecree());
                    } else honorsDecree.setCellValue("");
                }
            } else honorsDecree.setCellValue("");
        } else {
            honors.setCellValue("");
            honorsDate.setCellValue("");
            honorsDecree.setCellValue("");
        }


        Cell unit = tableRows[0].createCell(37);
        Cell country = tableRows[0].createCell(38);
        Cell locality = tableRows[0].createCell(39);
        Cell startOfMilitaryService = tableRows[0].createCell(40);
        Cell endOfMilitaryService = tableRows[0].createCell(41);
        if (selectedPerson.getMilitaryTerms() != null) {
            //Подразделение
            if (selectedPerson.getMilitaryTerms().size() != 0) {
                for (int i = 0; i < selectedPerson.getMilitaryTerms().size(); i++) {
                    if (i != 0) unit = tableRows[i].createCell(37);
                    if (selectedPerson.getMilitaryTerms().get(i).getUnit() != null) {
                        unit.setCellValue(selectedPerson.getMilitaryTerms().get(i).getUnit());
                    } else unit.setCellValue("");
                }
            } else unit.setCellValue("");
            //Страна
            if (selectedPerson.getMilitaryTerms().size() != 0) {
                for (int i = 0; i < selectedPerson.getMilitaryTerms().size(); i++) {
                    if (i != 0) country = tableRows[i].createCell(38);
                    if (selectedPerson.getMilitaryTerms().get(i).getCountry() != null) {
                        country.setCellValue(selectedPerson.getMilitaryTerms().get(i).getCountry());
                    } else country.setCellValue("");
                }
            } else country.setCellValue("");
            //Место службы
            if (selectedPerson.getMilitaryTerms().size() != 0) {
                for (int i = 0; i < selectedPerson.getMilitaryTerms().size(); i++) {
                    if (i != 0) locality = tableRows[i].createCell(39);
                    if (selectedPerson.getMilitaryTerms().get(i).getLocality() != null) {
                        locality.setCellValue(selectedPerson.getMilitaryTerms().get(i).getLocality());
                    } else locality.setCellValue("");
                }
            } else locality.setCellValue("");
            //Начало службы
            if (selectedPerson.getMilitaryTerms().size() != 0) {
                for (int i = 0; i < selectedPerson.getMilitaryTerms().size(); i++) {
                    if (i != 0) startOfMilitaryService = tableRows[i].createCell(40);
                    if (selectedPerson.getMilitaryTerms().get(i).getStartOfMilitaryService() != null) {
                        startOfMilitaryService.setCellValue(selectedPerson.getMilitaryTerms().get(i).getStartOfMilitaryService().toString());
                    } else startOfMilitaryService.setCellValue("");
                }
            } else startOfMilitaryService.setCellValue("");
            //Конец службы
            if (selectedPerson.getMilitaryTerms().size() != 0) {
                for (int i = 0; i < selectedPerson.getMilitaryTerms().size(); i++) {
                    if (i != 0) endOfMilitaryService = tableRows[i].createCell(41);
                    if (selectedPerson.getMilitaryTerms().get(i).getEndOfMilitaryService() != null) {
                        endOfMilitaryService.setCellValue(selectedPerson.getMilitaryTerms().get(i).getEndOfMilitaryService().toString());
                    } else endOfMilitaryService.setCellValue("");
                }
            } else endOfMilitaryService.setCellValue("");
        } else {
            unit.setCellValue("");
            country.setCellValue("");
            locality.setCellValue("");
            startOfMilitaryService.setCellValue("");
            endOfMilitaryService.setCellValue("");
        }


        Cell organisation = tableRows[0].createCell(42);
        Cell localityOfOrganisation = tableRows[0].createCell(43);
        Cell positionOfOrganisation = tableRows[0].createCell(44);
        if (selectedPerson.getWorkPlaces() != null) {
            //Место работы
            if (selectedPerson.getWorkPlaces().size() != 0) {
                for (int i = 0; i < selectedPerson.getWorkPlaces().size(); i++) {
                    if (i != 0) organisation = tableRows[i].createCell(42);
                    if (selectedPerson.getWorkPlaces().get(i).getOrganization() != null) {
                        organisation.setCellValue(selectedPerson.getWorkPlaces().get(i).getOrganization());
                    } else organisation.setCellValue(" ");
                }
            } else organisation.setCellValue("");
            // Расположение места работы
            if (selectedPerson.getWorkPlaces().size() > 0) {
                for (int i = 0; i < selectedPerson.getWorkPlaces().size(); i++) {
                    if (i != 0) localityOfOrganisation = tableRows[i].createCell(43);
                    if (selectedPerson.getWorkPlaces().get(i).getLocality() != null) {
                        localityOfOrganisation.setCellValue(selectedPerson.getWorkPlaces().get(i).getLocality());
                    } else localityOfOrganisation.setCellValue("");
                }
            } else localityOfOrganisation.setCellValue("");
            //Должность
            if (selectedPerson.getWorkPlaces().size() > 0) {
                for (int i = 0; i < selectedPerson.getWorkPlaces().size(); i++) {
                    if (i != 0) positionOfOrganisation = tableRows[i].createCell(44);
                    if (selectedPerson.getWorkPlaces().get(i).getPosition() != null) {
                        positionOfOrganisation.setCellValue(selectedPerson.getWorkPlaces().get(i).getPosition());
                    } else positionOfOrganisation.setCellValue(" ");
                }
            } else positionOfOrganisation.setCellValue(" ");
        } else {
            organisation.setCellValue("");
            localityOfOrganisation.setCellValue("");
            positionOfOrganisation.setCellValue("");
        }


        Cell typeOfWound = tableRows[0].createCell(45);
        Cell dateOfWound = tableRows[0].createCell(46);
        Cell disabilityOfWound = tableRows[0].createCell(47);
        if (selectedPerson.getWounds() != null) {
            //Ранение
            if (selectedPerson.getWounds().size() > 0) {
                for (int i = 0; i < selectedPerson.getWounds().size(); i++) {
                    if (i != 0) typeOfWound = tableRows[i].createCell(45);
                    if (selectedPerson.getWounds().get(i).getWoundType() != null) {
                        typeOfWound.setCellValue(selectedPerson.getWounds().get(i).getWoundType().getType());
                    } else typeOfWound.setCellValue("");
                }
            } else typeOfWound.setCellValue("");
            // Дата получения
            if (selectedPerson.getWounds().size() > 0) {
                for (int i = 0; i < selectedPerson.getWounds().size(); i++) {
                    if (i != 0) dateOfWound = tableRows[i].createCell(46);
                    if (selectedPerson.getWounds().get(i).getDate() != null) {
                        dateOfWound.setCellValue(selectedPerson.getWounds().get(i).getDate().toString());
                    } else dateOfWound.setCellValue("");
                }
            } else dateOfWound.setCellValue("");
            // Тяжесть ранения
            if (selectedPerson.getWounds().size() > 0) {
                for (int i = 0; i < selectedPerson.getWounds().size(); i++) {
                    if (i != 0) disabilityOfWound = tableRows[i].createCell(47);
                    if (selectedPerson.getWounds().get(i).getWoundDisability() != null) {
                        disabilityOfWound.setCellValue(selectedPerson.getWounds().get(i).getWoundDisability().getDisability());
                    } else disabilityOfWound.setCellValue("");
                }
            } else disabilityOfWound.setCellValue("");
        } else {
            typeOfWound.setCellValue("");
            dateOfWound.setCellValue("");
            disabilityOfWound.setCellValue("");
        }


        for (int i = 0; i < 48; i++) {
            sheet.autoSizeColumn(i);
        }
        return tableRowsNumber;
    }
}
