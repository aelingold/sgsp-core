INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (1,curdate(),'Gasista',true,'GAS',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (2,curdate(),'Pintor',true,'PIN',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (3,curdate(),'Plomero',true,'PLO',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (4,curdate(),'Electricista',true,'ELE',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (5,curdate(),'Cerrajero',true,'CER',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (6,curdate(),'Albañil',true,'ALB',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (7,curdate(),'Flete',true,'FLE',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (8,curdate(),'Service Aire Acondicionado',true,'SAA',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`code`,`updated_at`) VALUES (9,curdate(),'Service Linea Blanca',true,'SLB',null);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Revisar una perdida de gas',true,'WORK',null,1,'W1RPER');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Habilitar residencia',true,'WORK',null,1,'W1HRES');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Hacer una instalación',true,'WORK',null,1,'W1HINS');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Hacer un service',true,'WORK',null,1,'W1HSER');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,1,'W1ROTR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Calefon',true,'ARTIFACT',null,1,'A1CALE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Termotanque',true,'ARTIFACT',null,1,'A1TERMO');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Cocina/Horno',true,'ARTIFACT',null,1,'A1COHO');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Losa Radiante',true,'ARTIFACT',null,1,'A1LORA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Estufa',true,'ARTIFACT',null,1,'A1ESTU');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'ARTIFACT',null,1,'A1OTRO');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Casa',true,'PROPERTY',null,1,'P1CASA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Departamento',true,'PROPERTY',null,1,'P1DEPAR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Pintura de interior',true,'WORK',null,2,'W2PINT');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Pintura de exterior',true,'WORK',null,2,'W2PEXT');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Pintura de techo',true,'WORK',null,2,'W2PTEC');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Pintura de aberturas',true,'WORK',null,2,'W2PABE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lijado y enduido',true,'WORK',null,2,'W2LIEN');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Revoque',true,'WORK',null,2,'W2REVO');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,2,'W2ROTR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Casa',true,'PROPERTY',null,2,'P2CASA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Departamento',true,'PROPERTY',null,2,'P2DEPA');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Arreglar',true,'WORK',null,3,'W3ARRE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Sustituir',true,'WORK',null,3,'W3SUST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Instalar',true,'WORK',null,3,'W3INST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,3,'W3ROTR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Esta tapado',true,'PROBLEM',null,3,'P3ETAP');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Pierde agua',true,'PROBLEM',null,3,'P3PAGU');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'No sale agua caliente',true,'PROBLEM',null,3,'P3NSAC');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Poca presion',true,'PROBLEM',null,3,'P3POPR');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'PROBLEM',null,3,'P3OTRO');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Instalar',true,'WORK',null,4,'W4INST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Reparar',true,'WORK',null,4,'W4REPA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Cablear',true,'WORK',null,4,'W4CABL');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,4,'W4ROTR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Inodoro',true,'ARTIFACT',null,4,'A4INOD');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Ducha/Bañera',true,'ARTIFACT',null,4,'A4DUBA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lavatorio/Bacha',true,'ARTIFACT',null,4,'A4LABA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Bidet',true,'ARTIFACT',null,4,'A4BIDE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Canilla',true,'ARTIFACT',null,4,'A4CANI');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lavarropa',true,'ARTIFACT',null,4,'A4LARO');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lavavajilla',true,'ARTIFACT',null,4,'A4LAJI');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Cañeria',true,'ARTIFACT',null,4,'A4CANE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'ARTIFACT',null,4,'A4OTRO');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Instalar/cambiar una cerradura',true,'WORK',null,5,'W5INST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Reparar una cerradura',true,'WORK',null,5,'W5RECE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,5,'W5REOT');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Auto',true,'LOCK',null,5,'L5AUTO');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Casa/Departamento',true,'LOCK',null,5,'L5CADE');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Arreglar una pared',true,'WORK',null,6,'W6ARPA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Levantar una pared',true,'WORK',null,6,'W6LEPA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Arreglar un piso',true,'WORK',null,6,'W6ARPI');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Construir una habitacion',true,'WORK',null,6,'W6COHA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar un trabajo de durlock',true,'WORK',null,6,'W6RDUR');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,6,'W6ROTR');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Arreglar',true,'WORK',null,8,'W8ARRE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Sustituir',true,'WORK',null,8,'W8SUST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Instalar',true,'WORK',null,8,'W8INSTA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Recargar',true,'WORK',null,8,'W8RECA');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'BGH',true,'BRAND',null,8,'B8BGHH');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Candy',true,'BRAND',null,8,'B8CAND');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Coventry',true,'BRAND',null,8,'B8COVE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Electra',true,'BRAND',null,8,'B8ELEC');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Electrolux',true,'BRAND',null,8,'B8ELEX');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Hitachi',true,'BRAND',null,8,'B8HITA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'LG',true,'BRAND',null,8,'B8LGLG');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Panasonic',true,'BRAND',null,8,'B8PANA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Philips',true,'BRAND',null,8,'B8PHIS');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Philco',true,'BRAND',null,8,'BHPHIL');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Surrey',true,'BRAND',null,8,'B8SURR');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Toshiba',true,'BRAND',null,8,'B8TOSH');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'BRAND',null,8,'B8OTRO');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Split',true,'AIR_SYSTEM',null,8,'A8SPLI');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Central',true,'AIR_SYSTEM',null,8,'A8CENT');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'AIR_SYSTEM',null,8,'A8OTRO');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Arreglar',true,'WORK',null,9,'W9ARRE');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Sustituir',true,'WORK',null,9,'W9SUST');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Instalar',true,'WORK',null,9,'W9INST');

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lavarropa',true,'APPLIANCE',null,9,'A9LAPA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Lavavajilla',true,'APPLIANCE',null,9,'A9LAJI');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Microonda',true,'APPLIANCE',null,9,'A9MICR');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Heladera',true,'APPLIANCE',null,9,'A9HELA');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Horno',true,'APPLIANCE',null,9,'A9HORN');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Anafe',true,'APPLIANCE',null,9,'A9ANAF');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Campana',true,'APPLIANCE',null,9,'A9CAMP');
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`,`code`) VALUES (curdate(),'Otro',true,'APPLIANCE',null,9,'A9OTRO');

INSERT INTO `singuia`.`work_area_questions` (`created_at`,`description`,`group_type`,`updated_at`,`work_area_id`) VALUES (now(),'¿Qué tipo de inmueble es?','PROPERTY',null,1);
INSERT INTO `singuia`.`work_area_questions` (`created_at`,`description`,`group_type`,`updated_at`,`work_area_id`) VALUES (now(),'¿Qué hay que hacer?','WORK',null,1);
INSERT INTO `singuia`.`work_area_questions` (`created_at`,`description`,`group_type`,`updated_at`,`work_area_id`) VALUES (now(),'¿Qué artefacto es?','ARTIFACT',null,1);

INSERT INTO `singuia`.`work_area_questions` (`created_at`,`description`,`group_type`,`updated_at`,`work_area_id`) VALUES (now(),'¿Qué trabajo hay que hacer?','WORK',null,2);
INSERT INTO `singuia`.`work_area_questions` (`created_at`,`description`,`group_type`,`updated_at`,`work_area_id`) VALUES (now(),'¿Qué tipo de inmueble es?','PROPERTY',null,2);