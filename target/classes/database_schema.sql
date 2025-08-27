-- Task Management Database Schema
-- SQL Server Database Tables

-- Create Users table
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    is_admin BIT NOT NULL DEFAULT 0,
    created_date DATETIME2 DEFAULT GETDATE(),
    updated_date DATETIME2 DEFAULT GETDATE()
);

-- Create Tasks table
CREATE TABLE Tasks (
    task_id INT IDENTITY(1,1) PRIMARY KEY,
    title NVARCHAR(200) NOT NULL,
    due_date DATETIME2 NOT NULL,
    priority NVARCHAR(20) NOT NULL CHECK (priority IN ('Low', 'Medium', 'High')),
    status NVARCHAR(20) NOT NULL CHECK (status IN ('Pending', 'In_progress', 'Completed')) DEFAULT 'Pending',
    created_by_id INT NOT NULL,
    assigned_to_id INT,
    created_date DATETIME2 DEFAULT GETDATE(),
    updated_date DATETIME2 DEFAULT GETDATE(),
    
    -- Foreign key constraints
    CONSTRAINT FK_Tasks_CreatedBy FOREIGN KEY (created_by_id) REFERENCES Users(id),
    CONSTRAINT FK_Tasks_AssignedTo FOREIGN KEY (assigned_to_id) REFERENCES Users(id)
);

-- Create indexes for better performance
CREATE INDEX IX_Tasks_CreatedBy ON Tasks(created_by_id);
CREATE INDEX IX_Tasks_AssignedTo ON Tasks(assigned_to_id);
CREATE INDEX IX_Tasks_Status ON Tasks(status);
CREATE INDEX IX_Tasks_Priority ON Tasks(priority);
CREATE INDEX IX_Tasks_DueDate ON Tasks(due_date);

-- Create SubTasks table for GroupedTask functionality
CREATE TABLE SubTasks (
    id INT IDENTITY(1,1) PRIMARY KEY,
    parent_task_id INT NOT NULL,
    child_task_id INT NOT NULL,
    created_date DATETIME2 DEFAULT GETDATE(),
    
    -- Foreign key constraints
    CONSTRAINT FK_SubTasks_Parent FOREIGN KEY (parent_task_id) REFERENCES Tasks(task_id),
    CONSTRAINT FK_SubTasks_Child FOREIGN KEY (child_task_id) REFERENCES Tasks(task_id),
    
    -- Ensure a task cannot be a subtask of itself
    CONSTRAINT CHK_SubTasks_SelfReference CHECK (parent_task_id != child_task_id),
    
    -- Unique constraint to prevent duplicate relationships
    CONSTRAINT UK_SubTasks_Relationship UNIQUE (parent_task_id, child_task_id)
);

-- Create index for SubTasks
CREATE INDEX IX_SubTasks_Parent ON SubTasks(parent_task_id);
CREATE INDEX IX_SubTasks_Child ON SubTasks(child_task_id);

-- Insert sample data (matching your Main.java examples)
INSERT INTO Users (username, password, email, is_admin) VALUES
('admin', 'admin123', 'admin@gmail.com', 1),
('osama', '1234', 'osama@gmail.com', 0),
('ali', '1111', 'ali@gmail.com', 0);


-- Note: You may want to hash passwords in production instead of storing plain text

PRINT 'Database schema created successfully!';
