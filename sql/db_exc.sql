use schedule_management;
SET GLOBAL log_bin_trust_function_creators = 1;


DELIMITER //
CREATE FUNCTION uf_AutoGenerateIDByParameters (
  lastID VARCHAR(20),
  prefix VARCHAR(255),
  size INT
)
RETURNS VARCHAR(20)
BEGIN
  DECLARE nextID VARCHAR(20);
  DECLARE num_nextID INT;
  DECLARE new_size INT;
  
  IF lastID IS NULL OR lastID = '' THEN
    SET lastID = CONCAT(prefix, LPAD('', size - LENGTH(prefix), '0'));
  END IF;
  
  SET lastID = TRIM(lastID);
  SET num_nextID = CAST(REPLACE(lastID, prefix, '') AS UNSIGNED) + 1;
  SET new_size = size - LENGTH(prefix);
  
  SET nextID = CONCAT(prefix, LPAD(num_nextID, new_size, '0'));
  
  RETURN nextID;
END//
DELIMITER ;


DELIMITER $$
CREATE FUNCTION uf_AutoGenerateID(table_name VARCHAR(255)) 
RETURNS VARCHAR(20)
BEGIN
  DECLARE lastID VARCHAR(20);
  DECLARE prefix VARCHAR(255);
  DECLARE num INT;
  DECLARE nextID VARCHAR(20);

  CASE table_name
    WHEN 'teacher' THEN
      SELECT _id INTO lastID FROM teacher ORDER BY _id DESC LIMIT 1;
      SET prefix = 'TEA';
    WHEN 'classroom' THEN
      SELECT _id INTO lastID FROM classroom ORDER BY _id DESC LIMIT 1;
      SET prefix = 'ROOM';
    WHEN 'shift' THEN
      SELECT _id INTO lastID FROM shift ORDER BY _id DESC LIMIT 1;
      SET prefix = 'SHIFT';
    WHEN 'student' THEN
      SELECT _id INTO lastID FROM student ORDER BY _id DESC LIMIT 1;
      SET prefix = 'STU';
	WHEN 'notification' THEN
      SELECT _id INTO lastID FROM notification ORDER BY _id DESC LIMIT 1;
      SET prefix = 'NOTI';
	WHEN 'grade_detail' THEN
      SELECT _id INTO lastID FROM grade_detail ORDER BY _id DESC LIMIT 1;
      SET prefix = 'GRADE';
	WHEN 'attendance_record' THEN
      SELECT _id INTO lastID FROM attendance_record ORDER BY _id DESC LIMIT 1;
      SET prefix = 'REC';
  END CASE;
  
  SET num = 3 + LENGTH(TRIM(prefix));
  
  SET nextID = uf_AutoGenerateIDByParameters(lastID, prefix, num);
  
  RETURN nextID;
END $$
DELIMITER ;

drop trigger TG_AutoIncrID;
DELIMITER $$
CREATE TRIGGER TG_AutoIncrID 
BEFORE INSERT ON class_group
FOR EACH ROW
BEGIN
	DECLARE groupId VARCHAR(20);
	DECLARE classId VARCHAR(20);
	DECLARE sizeOfNum INT DEFAULT 2; -- (+2: 2 digits after the prefix character)
	SELECT NEW.class_id INTO classId;
    SELECT NEW._id INTO groupId;
    
	BEGIN
		DECLARE lastID VARCHAR(20);
		DECLARE prefix VARCHAR(20);
        DECLARE size INT;
        DECLARE nextID VARCHAR(20);
        
        SET prefix = classId; -- Assign the prefix as the character of maLop
        SET size  = LENGTH(TRIM(prefix)) + sizeOfNum; -- Get the length of the prefix
		
		-- Retrieve the most recent NHOMHOC.MaNhomHoc for the given maLop, excluding the newly inserted one
		SELECT _id INTO lastID FROM class_group
		WHERE class_id = classId AND _id != groupId
		ORDER BY _id DESC
		LIMIT 1;
		
		-- Call the function to generate the new ID for NHOMHOC
		SET nextID  = uf_AutoGenerateIDByParameters(lastID, prefix, size);
		
		-- Update the new ID for NHOMHOC
		SET NEW._id = nextID;
	END;
END $$
DELIMITER ;