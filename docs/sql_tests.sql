SELECT * FROM car_management_db.car_model;

DELETE FROM car_management_db.car
WHERE model = 'Macan';

commit;

SELECT VERSION();

ALTER TABLE car_model ADD COLUMN license_plate VARCHAR(255);
ALTER TABLE car_model CHANGE COLUMN plate license_plate VARCHAR(255);
ALTER TABLE car_model DROP COLUMN license_plate;