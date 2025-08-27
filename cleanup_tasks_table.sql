-- Fix Tasks table - remove duplicate due_date column
USE [Task Management];

-- Check current columns
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'Tasks' 
ORDER BY ORDINAL_POSITION;

-- Drop the unused due_date column (keep dueDate)
ALTER TABLE Tasks DROP COLUMN due_date;

-- Verify the fix
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE 
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'Tasks' 
ORDER BY ORDINAL_POSITION;
