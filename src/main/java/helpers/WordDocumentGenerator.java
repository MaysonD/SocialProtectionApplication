package helpers;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import model.entity.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordDocumentGenerator {

    public static void generateWordReport(VeteranEntity veteranEntity, File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);

            XWPFDocument doc = new XWPFDocument();

            /* ---Title Paragraph--- */
            XWPFParagraph titleParagraph = doc.createParagraph();
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleText = titleParagraph.createRun();
            titleText.setFontFamily("Times New Roman");
            titleText.setFontSize(16);
            titleText.setBold(true);
            titleText.setText("УЧЕТНАЯ КАРТОЧКА");

            /* ---Name Paragraph--- */
            String name = "";
            name += !veteranEntity.getSecondName().equals("") ? veteranEntity.getSecondName() : "";
            name += !veteranEntity.getFirstName().equals("") ? " " + veteranEntity.getFirstName() : "";
            name += !veteranEntity.getMiddleName().equals("") ? " " + veteranEntity.getMiddleName() : "";
            if (!name.equals("")) {
                XWPFParagraph nameParagraph = doc.createParagraph();
                nameParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun nameLabel = nameParagraph.createRun();
                nameLabel.setFontFamily("Times New Roman");
                nameLabel.setFontSize(14);
                nameLabel.setText("Фамилия, имя, отчество: ");

                XWPFRun nameValue = nameParagraph.createRun();
                nameValue.setFontFamily("Times New Roman");
                nameValue.setFontSize(14);
                nameValue.setBold(true);
                nameValue.setCapitalized(true);
                nameValue.setText(name);
            }

            /* ---Birth Paragraph---*/
            if (!veteranEntity.getDateOfBirth().toString().equals("")) {
                XWPFParagraph birthParagraph = doc.createParagraph();
                birthParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun birthLaber = birthParagraph.createRun();
                birthLaber.setFontFamily("Times New Roman");
                birthLaber.setFontSize(14);
                birthLaber.setText("Дата рождения: ");

                XWPFRun birthValue = birthParagraph.createRun();
                birthValue.setFontFamily("Times New Roman");
                birthValue.setFontSize(14);
                birthValue.setBold(true);
                birthValue.setCapitalized(true);
                birthValue.setText(veteranEntity.getDateOfBirth().toString());
            }

            /* ---Rank Paragraph---*/
            if (veteranEntity.getMilitaryRank() != null && !veteranEntity.getMilitaryRank().getName().equals("")) {
                XWPFParagraph rankParagraph = doc.createParagraph();
                rankParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun rankLabel = rankParagraph.createRun();
                rankLabel.setFontFamily("Times New Roman");
                rankLabel.setFontSize(14);
                rankLabel.setText("Воинское звание: ");

                XWPFRun rankValue = rankParagraph.createRun();
                rankValue.setFontFamily("Times New Roman");
                rankValue.setFontSize(14);
                rankValue.setBold(true);
                rankValue.setText(veteranEntity.getMilitaryRank().getName());
            }

            /* ---MilitaryTerm Paragraph---*/
            if (veteranEntity.getMilitaryTerms().size() > 0) {
                String term = "";
                MilitaryTermEntity militaryTermEntity = veteranEntity.getMilitaryTerms().get(veteranEntity.getMilitaryTerms().size() - 1);
                term += !militaryTermEntity.getStartOfMilitaryService().toString().equals("") ? "c " + militaryTermEntity.getStartOfMilitaryService().toString() : "";
                term += !militaryTermEntity.getEndOfMilitaryService().toString().equals("") ? " по " + militaryTermEntity.getEndOfMilitaryService().toString() : "";
                term += !militaryTermEntity.getCountry().equals("") ? ", " + militaryTermEntity.getCountry() : "";
                term += !militaryTermEntity.getLocality().equals("") ? ", " + militaryTermEntity.getLocality() : "";
                term += !militaryTermEntity.getUnit().equals("") ? ", " + militaryTermEntity.getUnit() : "";
                term += !veteranEntity.getPosition().equals("") ? ", " + veteranEntity.getPosition() : "";

                if (!term.equals("")) {
                    XWPFParagraph termParagraph = doc.createParagraph();
                    termParagraph.setAlignment(ParagraphAlignment.LEFT);

                    XWPFRun termLabel = termParagraph.createRun();
                    termLabel.setFontFamily("Times New Roman");
                    termLabel.setFontSize(14);
                    termLabel.setText("Период и место прохождения службы: ");


                    XWPFRun termValue = termParagraph.createRun();
                    termValue.setFontFamily("Times New Roman");
                    termValue.setFontSize(14);
                    termValue.setBold(true);
                    termValue.setText(term);
                }
            }

            /* ---Honors Paragraph---*/
            if (veteranEntity.getVeteranHonors().size() > 0) {

                XWPFParagraph honorParagraph = doc.createParagraph();
                honorParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun honorLabel = honorParagraph.createRun();
                honorLabel.setFontFamily("Times New Roman");
                honorLabel.setFontSize(14);
                honorLabel.setText("Награды: ");

                for (VeteranHonorEntity veteranHonor : veteranEntity.getVeteranHonors()) {
                    if (!veteranHonor.getHonor().getName().equals("")) {
                        XWPFRun honorValue = honorParagraph.createRun();
                        honorValue.setFontFamily("Times New Roman");
                        honorValue.setFontSize(14);
                        honorValue.setBold(true);
                        honorValue.setText(veteranHonor.getHonor().getName() + "; ");
                    }
                }

            }

            /* ---Wounds Paragraph---*/
            if (veteranEntity.getWounds().size() > 0) {
                XWPFParagraph woundParagraph = doc.createParagraph();
                woundParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun woundLabel = woundParagraph.createRun();
                woundLabel.setFontFamily("Times New Roman");
                woundLabel.setFontSize(14);
                woundLabel.setText("Ранения: ");

                for (VeteranWoundEntity wound : veteranEntity.getWounds()) {
                    if (!wound.getWoundType().getType().equals("")) {
                        XWPFRun woundValue = woundParagraph.createRun();
                        woundValue.setFontFamily("Times New Roman");
                        woundValue.setFontSize(14);
                        woundValue.setBold(true);
                        woundValue.setText(wound.getWoundType().getType() + "; ");
                    }
                }

            }

            /* ---Address Paragraph--- */
            if (!veteranEntity.getAddress().equals("")) {
                XWPFParagraph addressParagraph = doc.createParagraph();
                addressParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun addressLabel = addressParagraph.createRun();
                addressLabel.setFontFamily("Times New Roman");
                addressLabel.setFontSize(14);
                addressLabel.setText("Домашний адрес: ");

                XWPFRun addressValue = addressParagraph.createRun();
                addressValue.setFontFamily("Times New Roman");
                addressValue.setFontSize(14);
                addressValue.setBold(true);
                addressValue.setCapitalized(true);
                addressValue.setText(veteranEntity.getAddress());
            }

            /* ---Phone Paragraph--- */
            if (!veteranEntity.getPhoneNumber().equals("")) {
                XWPFParagraph phoneParagraph = doc.createParagraph();
                phoneParagraph.setAlignment(ParagraphAlignment.LEFT);

                XWPFRun phoneLabel = phoneParagraph.createRun();
                phoneLabel.setFontFamily("Times New Roman");
                phoneLabel.setFontSize(14);
                phoneLabel.setText("Номер телефона: ");

                XWPFRun phoneValue = phoneParagraph.createRun();
                phoneValue.setFontFamily("Times New Roman");
                phoneValue.setFontSize(14);
                phoneValue.setBold(true);
                phoneValue.setCapitalized(true);
                phoneValue.setText(veteranEntity.getPhoneNumber());
            }

            /* ---WorkPlace Paragraph---*/
            if (veteranEntity.getWorkPlaces().size() > 0) {
                String place = "";
                WorkPlaceEntity workPlaceEntity = veteranEntity.getWorkPlaces().get(veteranEntity.getWorkPlaces().size() - 1);
                place += !workPlaceEntity.getLocality().equals("") ? workPlaceEntity.getLocality() : "";
                place += !workPlaceEntity.getOrganization().equals("") ? ", " + workPlaceEntity.getOrganization() : "";
                place += !workPlaceEntity.getPosition().equals("") ? ", " + workPlaceEntity.getPosition() : "";

                if (!place.equals("")) {
                    XWPFParagraph placeParagraph = doc.createParagraph();
                    placeParagraph.setAlignment(ParagraphAlignment.LEFT);

                    XWPFRun placeLabel = placeParagraph.createRun();
                    placeLabel.setFontFamily("Times New Roman");
                    placeLabel.setFontSize(14);
                    placeLabel.setText("Место работы: ");


                    XWPFRun placeValue = placeParagraph.createRun();
                    placeValue.setFontFamily("Times New Roman");
                    placeValue.setFontSize(14);
                    placeValue.setBold(true);
                    placeValue.setText(place);
                }
            }


            doc.write(out);
            out.close();
            doc.close();
        } catch (IOException e) {

        }
    }
}
