package org.example.service;

import com.google.gson.Gson;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.ai.AiRagSchemaJson;
import org.example.ai.bank.ParseValue;
import org.example.bank.OutputClassBank.KDBContext;

//import org.example.output.vyta.client_med.AIMA_client_med;


import org.example.bank.OutputClassBank.KdbContextAi;
import org.example.output.vyta.client_med.AIMA_client_med;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.example.bank.AppConfig.getAiSchema;
import static org.example.bank.AppConfig.getGmaName;
import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;

@Component
public class TestService {

    @PersistenceContext(unitName = "entityManagerFactoryAi")
    EntityManager entityManager;

    @Transactional(transactionManager = "transactionManagerAi")
    public void test() throws Exception {
        System.out.println("test service is working");

        KDBContext kdbContext = KDBContext.KDB_CONTEXT;
        KdbContextAi kdbContextAi = KdbContextAi.KDB_CONTEXT_AI;

        AIMA_client_med aima_client_med = new AIMA_client_med();
        AiRagSchemaJson json = new Gson().fromJson(testJson, AiRagSchemaJson.class);
        Path file = new File("txt.txt").getAbsoluteFile().toPath();
        Files.writeString(file,
                """
                        {
                          "ragName": "client_med",
                          "description": "",
                          "tableName": "client_med",
                          "tableType": "ai_rag",
                          "columns": [
                            {
                              "name": "report_date",
                              "columnId": "col7f9c6415-b8f0-4ec0-87c5-4d11a74ee7b3",
                              "description": "",
                              "tags": [""],
                              "isNullable": false,
                              "isEditable": true,
                              "isUnique": false,
                              "isRequired": false,
                              "type": "",
                              "defaultValue": "",
                              "columnGroups": ["default", "ai_group"],
                              "isUniqueIdentifier": false,
                              "uniqueIdentifierGroups": [""],
                              "isIndex": false,
                              "indexGroups": [""],
                              "referenceColumns": null
                            },
                            {
                              "name": "people_id",
                              "columnId": "col1e562c75-ccd7-4157-9495-7ac84e452595",
                              "description": "",
                              "tags": [""],
                              "isNullable": false,
                              "isEditable": true,
                              "isUnique": false,
                              "isRequired": false,
                              "type": "",
                              "defaultValue": "",
                              "columnGroups": ["default", "ai_group"],
                              "isUniqueIdentifier": false,
                              "uniqueIdentifierGroups": [""],
                              "isIndex": false,
                              "indexGroups": [""],
                              "referenceColumns": null
                            }
                          ]
                        }
                        """, StandardCharsets.UTF_8);





        aima_client_med.setCol(aima_client_med.getK_report_date()
                .setEntityValue(
                        Timestamp.valueOf(LocalDate.now().atStartOfDay())
                ));
        aima_client_med.setCol(aima_client_med.getPk_people_id().setEntityValue("12345"));
        aima_client_med.setCol(aima_client_med.getAiCOL_measurement_date().setEntityValue(Timestamp.from(Instant.now())));





//        String jsonObj =  "{'upload_name': 'client_med_upload_group', 'upload_description': 'A clinical record of patient vital signs including heart rate, blood pressure, temperature, oxygen saturation, and respiratory rate.', 'timestamp': '04/24/2026', 'groups': [{'group_name': 'vital_signs', 'description': 'Core physiological measurements taken at the time of the clinical encounter.', 'types': [{'type_name': 'cardiovascular', 'description': 'Measurements related to heart rate and blood pressure.', 'names': [{'input_name': 'heart_rate', 'description': 'The number of heartbeats per minute recorded at the time of assessment.', 'values': [{'value': {'name': 'hr', 'description': 'A quantitative measurement of the number of cardiac cycles occurring per minute.', 'value': 74, 'unit': 'bpm', 'normalized_value': 74, 'normalized_unit': 'bpm', 'measurement_type': 'quantity', 'date': '04/24/2026', 'time': None, 'frequency': None, 'source': {'type': 'document', 'name': 'Vital Signs Record', 'page': None}, 'confidence': None, 'notes': None}}]}, {'input_name': 'blood_pressure', 'description': 'Systolic and diastolic arterial blood pressure recorded at the time of assessment.', 'values': [{'value': {'name': 'bp', 'description': 'A quantitative measurement of arterial pressure expressed as systolic over diastolic values in millimeters of mercury.', 'value': '110/72', 'unit': 'mmHg', 'normalized_value': None, 'normalized_unit': None, 'measurement_type': 'quantity', 'date': '04/24/2026', 'time': None, 'frequency': None, 'source': {'type': 'document', 'name': 'Vital Signs Record', 'page': None}, 'confidence': None, 'notes': 'Systolic: 110 mmHg, Diastolic: 72 mmHg'}}]}]}, {'type_name': 'respiratory_and_oxygenation', 'description': 'Measurements related to respiratory rate and peripheral oxygen saturation.', 'names': [{'input_name': 'respiratory_rate', 'description': 'The number of breaths taken per minute recorded at the time of assessment.', 'values': [{'value': {'name': 'rr', 'description': 'A quantitative measurement of the number of respiratory cycles occurring per minute.', 'value': 18, 'unit': 'breaths/min', 'normalized_value': 18, 'normalized_unit': 'breaths/min', 'measurement_type': 'quantity', 'date': '04/24/2026', 'time': None, 'frequency': None, 'source': {'type': 'document', 'name': 'Vital Signs Record', 'page': None}, 'confidence': None, 'notes': None}}]}, {'input_name': 'oxygen_saturation', 'description': 'Peripheral oxygen saturation measured via pulse oximetry at the time of assessment.', 'values': [{'value': {'name': 'spo2', 'description': 'A quantitative measurement of the percentage of hemoglobin saturated with oxygen as measured non-invasively by pulse oximetry.', 'value': 96, 'unit': '%', 'normalized_value': 96, 'normalized_unit': '%', 'measurement_type': 'percentage', 'date': '04/24/2026', 'time': None, 'frequency': None, 'source': {'type': 'document', 'name': 'Vital Signs Record', 'page': None}, 'confidence': None, 'notes': None}}]}]}, {'type_name': 'temperature', 'description': 'Body temperature measurement recorded at the time of assessment.', 'names': [{'input_name': 'temperature', 'description': 'Core or peripheral body temperature recorded at the time of assessment.', 'values': [{'value': {'name': 'temp', 'description': 'A quantitative measurement of body temperature expressed in degrees Fahrenheit.', 'value': 98, 'unit': '°F', 'normalized_value': 36.7, 'normalized_unit': '°C', 'measurement_type': 'quantity', 'date': '04/24/2026', 'time': None, 'frequency': None, 'source': {'type': 'document', 'name': 'Vital Signs Record', 'page': None}, 'confidence': None, 'notes': 'Normalized to Celsius using standard conversion formula: (°F - 32) × 5/9'}}]}]}]}]}"
//                .replace("'", "\"");

//        System.out.println(jsonObj2);
//        aima_client_med.addDbSkeleton(List.of(new File("/Users/kaedenbradshaw/Desktop/Summer/VitalsSummer.txt")),  "client_med_upload_group",entityManager);

        kdbContextAi.reloadRag(aima_client_med, List.of(new File("/Users/kaedenbradshaw/Desktop/Summer/VitalsSummer.txt")), "client_med_upload_group",entityManager);


    }
    String testJson = """
            {
              "upload_name": "vyta_functional",
              "reported_at": null,
              "groups": [
                {
                  "group_name": "gait",
                  "types": [
                    {
                      "type_name": "gait_speed",
                      "names": [
                        {
                          "input_name": "gait_speed",
                          "values": [
                            {
                              "value": {
                                "name": "walking_speed",
                                "description": null,
                                "value": 1.33,
                                "unit": "m/s",
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": "ratio",
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null
                              },
                              "notes": "Patient completed 30ft in 6.9 sec"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                },
                {
                  "group_name": "balance",
                  "types": [
                    {
                      "type_name": "single_leg_balance",
                      "names": [
                        {
                          "input_name": "single_leg_balance_left",
                          "values": [
                            {
                              "value": {
                                "name": "balance_duration",
                                "description": null,
                                "value": 30,
                                "unit": "sec",
                                "normalized_value": 30,
                                "normalized_unit": "s",
                                "measurement_type": "quantity",
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null,
                                "reference_range": {
                                  "min": 30,
                                  "max": null,
                                  "unit": "sec"
                                },
                                "classification": null,
                                "change_from_baseline": null,
                                "clinical_interpretation": "measuring neuromuscular control, fall risk, brain + proprioception function"
                              },
                              "notes": "patient balanced on left leg 30 sec. / right leg 30 sec. (stand on one leg, eyes open >30sec)"
                            }
                          ]
                        },
                        {
                          "input_name": "single_leg_balance_right",
                          "values": [
                            {
                              "value": {
                                "name": "balance_duration",
                                "description": null,
                                "value": 30,
                                "unit": "sec",
                                "normalized_value": 30,
                                "normalized_unit": "s",
                                "measurement_type": "quantity",
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null,
                                "reference_range": {
                                  "min": 30,
                                  "max": null,
                                  "unit": "sec"
                                },
                                "classification": null,
                                "change_from_baseline": null,
                                "clinical_interpretation": "measuring neuromuscular control, fall risk, brain + proprioception function"
                              },
                              "notes": "patient balanced on left leg 30 sec. / right leg 30 sec. (stand on one leg, eyes open >30sec)"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                },
                {
                  "group_name": "strength",
                  "types": [
                    {
                      "type_name": "farmer_carry",
                      "names": [
                        {
                          "input_name": "farmer_carry",
                          "values": [
                            {
                              "value": {
                                "name": "carry_load",
                                "description": null,
                                "value": 70,
                                "unit": "lbs",
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": "quantity",
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null,
                                "classification": "moderate performance",
                                "change_from_baseline": null,
                                "clinical_interpretation": null
                              },
                              "notes": "Patient carried 70 lbs (45% body weight) for sec with moderate performance"
                            },
                            {
                              "value": {
                                "name": "carry_duration",
                                "description": null,
                                "value": null,
                                "unit": null,
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": null,
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null,
                                "classification": "moderate performance",
                                "change_from_baseline": null,
                                "clinical_interpretation": null
                              },
                              "notes": "Patient carried 70 lbs (45% body weight) for sec with moderate performance"
                            }
                          ]
                        }
                      ]
                    }
                  ]
                },
                {
                  "group_name": "mobility",
                  "types": [
                    {
                      "type_name": "sit_to_stand_floor_rise",
                      "names": [
                        {
                          "input_name": "sit_to_stand_floor_rise",
                          "values": [
                            {
                              "value": {
                                "name": "rise_time",
                                "description": null,
                                "value": null,
                                "unit": null,
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": null,
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null,
                                "classification": null,
                                "change_from_baseline": null,
                                "clinical_interpretation": "sit to stand with arm assistance, found it difficult"
                              },
                              "notes": "controlled (y/n) N; speed wnl (y/n) Y; assistance needed (y/n) Y; sit to stand with arm assistance, found it difficult"
                            }
                          ]
                        }
                      ]
                    },
                    {
                      "type_name": "timed_up_and_go",
                      "names": [
                        {
                          "input_name": "timed_up_and_go",
                          "values": [
                            {
                              "value": {
                                "name": "tug_speed",
                                "description": null,
                                "value": null,
                                "unit": null,
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": null,
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null
                              },
                              "notes": null
                            },
                            {
                              "value": {
                                "name": "tug_time",
                                "description": null,
                                "value": null,
                                "unit": null,
                                "normalized_value": null,
                                "normalized_unit": null,
                                "measurement_type": null,
                                "date": null,
                                "time": null,
                                "frequency": null,
                                "source": null
                              },
                              "notes": null
                            }
                          ]
                        }
                      ]
                    }
                  ]
                }
              ]
            }
            """;



}
