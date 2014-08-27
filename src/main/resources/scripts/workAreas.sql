INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (1,curdate(),null,true,'Gasista',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (2,curdate(),null,true,'Pintor',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (3,curdate(),null,true,'Plomero',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (4,curdate(),null,true,'Electricista',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (5,curdate(),null,true,'Cerrajero',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (6,curdate(),null,true,'Albañil',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (7,curdate(),null,true,'Flete',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (8,curdate(),null,true,'Service Aire Acondicionado',null);
INSERT INTO `singuia`.`work_areas` (`id`,`created_at`,`description`,`is_enabled`,`name`,`updated_at`) VALUES (9,curdate(),null,true,'Service Linea Blanca',null);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Revisar una perdida de gas',true,'WORK',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Habilitar residencia',true,'WORK',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Hacer una instalación',true,'WORK',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Hacer un service',true,'WORK',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,1);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Calefon',true,'ARTIFACT',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Termotanque',true,'ARTIFACT',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Cocina/Horno',true,'ARTIFACT',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Losa Radiante',true,'ARTIFACT',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Estufa',true,'ARTIFACT',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'ARTIFACT',null,1);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Casa',true,'PROPERTY',null,1);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Departamento',true,'PROPERTY',null,1);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Pintura de interior',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Pintura de exterior',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Pintura de techo',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Pintura de aberturas',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lijado y enduido',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Revoque',true,'WORK',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,2);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Casa',true,'PROPERTY',null,2);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Departamento',true,'PROPERTY',null,2);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Arreglar',true,'WORK',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Sustituir',true,'WORK',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Instalar',true,'WORK',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,3);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Esta tapado',true,'PROBLEM',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Pierde agua',true,'PROBLEM',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'No sale agua caliente',true,'PROBLEM',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Poca presion',true,'PROBLEM',null,3);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'PROBLEM',null,3);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Instalar',true,'WORK',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Reparar',true,'WORK',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Cablear',true,'WORK',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,4);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Inodoro',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Ducha/Bañera',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lavatorio/Bacha',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Bidet',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Canilla',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lavarropa',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lavavajilla',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Cañeria',true,'ARTIFACT',null,4);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'ARTIFACT',null,4);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Instalar/cambiar una cerradura',true,'WORK',null,5);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Reparar una cerradura',true,'WORK',null,5);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,5);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Auto',true,'LOCK',null,5);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Casa/Departamento',true,'LOCK',null,5);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Arreglar una pared',true,'WORK',null,6);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Levantar una pared',true,'WORK',null,6);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Arreglar un piso',true,'WORK',null,6);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Construir una habitacion',true,'WORK',null,6);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar un trabajo de durlock',true,'WORK',null,6);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Realizar otro trabajo',true,'WORK',null,6);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Arreglar',true,'WORK',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Sustituir',true,'WORK',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Instalar',true,'WORK',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Recargar',true,'WORK',null,8);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'BGH',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Candy',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Coventry',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Electra',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Electrolux',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Hitachi',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'LG',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Panasonic',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Philips',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Philco',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Surrey',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Toshiba',true,'BRAND',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'BRAND',null,8);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Split',true,'AIR_SYSTEM',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Central',true,'AIR_SYSTEM',null,8);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'AIR_SYSTEM',null,8);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Arreglar',true,'WORK',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Sustituir',true,'WORK',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Instalar',true,'WORK',null,9);

INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lavarropa',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Lavavajilla',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Microonda',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Heladera',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Horno',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Anafe',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Campana',true,'APPLIANCE',null,9);
INSERT INTO `singuia`.`work_area_items` (`created_at`,`description`,`is_enabled`,`group_type`,`updated_at`,`work_area_id`) VALUES (curdate(),'Otro',true,'APPLIANCE',null,9);
