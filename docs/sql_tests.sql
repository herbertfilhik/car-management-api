SELECT * FROM car_management_db.car_model;

DELETE FROM car_management_db.car
WHERE model = 'Macan';

commit;

SELECT VERSION();

ALTER TABLE car ADD COLUMN Plate VARCHAR(255);

