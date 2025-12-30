CREATE DATABASE scarlet_shade;
USE scarlet_shade;

CREATE TABLE users(
	id_user BIGINT AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL, 
    password_user VARCHAR(255) NOT NULL,
    soundtrack DECIMAL(10, 2) NOT NULL DEFAULT(0.3),
    sound_effect DECIMAL(10, 2) NOT NULL DEFAULT(0.5),
    
    CHECK(soundtrack >= 0 AND soundtrack <= 1),
    CHECK(sound_effect >= 0 AND sound_effect <= 1),
    PRIMARY KEY(id_user)
);

CREATE TABLE keyboard_control(
	id_control BIGINT AUTO_INCREMENT,
    id_user BIGINT UNIQUE NOT NULL,
    move_up VARCHAR(255) NOT NULL DEFAULT('W'),
    move_down VARCHAR(255) NOT NULL DEFAULT('S'),
    move_left VARCHAR(255) NOT NULL DEFAULT('A'),
    move_right VARCHAR(255) NOT NULL DEFAULT('D'),
    jump VARCHAR(255) NOT NULL DEFAULT('SPACE'),
    dash VARCHAR(255) NOT NULL DEFAULT('SHIFT'),
    crouch VARCHAR(255) NOT NULL DEFAULT('CONTROL'),
    attack VARCHAR(255) NOT NULL DEFAULT('J'),
    spin_attack VARCHAR(255) NOT NULL DEFAULT('K'),
    especial_move_one VARCHAR(255) NOT NULL DEFAULT('N'),
    especial_move_two VARCHAR(255) NOT NULL DEFAULT('M'),
    menu_access VARCHAR(255) NOT NULL DEFAULT('ESC'),
    select_itens VARCHAR(255) NOT NULL DEFAULT('Q'),
    use_item VARCHAR(255) NOT NULL DEFAULT('E'),
    
    PRIMARY KEY(id_control),
    FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE gamepad_control(
	id_control BIGINT AUTO_INCREMENT,
    id_user BIGINT UNIQUE NOT NULL,
    move_up VARCHAR(255) NOT NULL DEFAULT('ANALOG_UP'),
    move_down VARCHAR(255) NOT NULL DEFAULT('ANALOG_DOWN'),
    move_left VARCHAR(255) NOT NULL DEFAULT('ANALOG_LEFT'),
    move_right VARCHAR(255) NOT NULL DEFAULT('ANALOG_RIGHT'),
    jump VARCHAR(255) NOT NULL DEFAULT('A'),
    dash VARCHAR(255) NOT NULL DEFAULT('B'),
    crouch VARCHAR(255) NOT NULL DEFAULT('ANALOG_CLICK'),
    attack VARCHAR(255) NOT NULL DEFAULT('RIGHT_TRIGGER'),
    spin_attack VARCHAR(255) NOT NULL DEFAULT('LEFT_TRIGGER'),
    especial_move_one VARCHAR(255) NOT NULL DEFAULT('X'),
    especial_move_two VARCHAR(255) NOT NULL DEFAULT('Y'),
    menu_access VARCHAR(255) NOT NULL DEFAULT('START'),
    select_itens VARCHAR(255) NOT NULL DEFAULT('RIGHT_BUMPER'),
    use_item VARCHAR(255) NOT NULL DEFAULT('LEFT_BUMPER'),
    
    PRIMARY KEY(id_control),
    FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE slot(
	id_slot BIGINT AUTO_INCREMENT,
    id_user BIGINT NOT NULL,
    number_slot INT NOT NULL,
    game_completed BOOLEAN NOT NULL DEFAULT(FALSE),
    
    CHECK(number_slot >= 1 AND number_slot <= 4),
    PRIMARY KEY(id_slot),
    FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE player(
	id_player BIGINT AUTO_INCREMENT,
    id_slot BIGINT UNIQUE NOT NULL,
    damage INT NOT NULL DEFAULT(5),
    speed INT NOT NULL DEFAULT(5),
    life INT NOT NULL DEFAULT(100),
    max_life INT NOT NULL DEFAULT(100),
    element VARCHAR(255),
    current_yokai VARCHAR(255),
    money INT NOT NULL DEFAULT(0),
    
    CHECK(damage > 0 AND speed > 0 AND max_life> 0 AND life >0 AND money >= 0),
    CHECK(element IN ('FIRE', 'WATER', 'LIGHT', 'AIR')),
    PRIMARY KEY(id_player),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE jade(
	id_jade BIGINT AUTO_INCREMENT,
    id_slot BIGINT NOT NULL,
    name_jade VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id_jade),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE yokai(
	id_yokai BIGINT AUTO_INCREMENT,
    id_slot BIGINT NOT NULL,
    name_yokai VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id_yokai),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE side_quest(
	id_side_quest BIGINT AUTO_INCREMENT,
    id_slot BIGINT NOT NULL,
    name_side_quest VARCHAR(255) NOT NULL,
    status_side_quest VARCHAR(30) NOT NULL DEFAULT('PROGRESS'),
    
    CHECK(status_side_quest IN ('PROGRESS', 'COMPLETED')),
    PRIMARY KEY(id_side_quest),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE world_progress(
	id_world_progress BIGINT AUTO_INCREMENT,
    id_slot BIGINT UNIQUE NOT NULL,
    current_phase VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id_world_progress),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE phases(
	id_phase BIGINT AUTO_INCREMENT,
    id_world_progress BIGINT NOT NULL,
    status_phase VARCHAR(255) NOT NULL DEFAULT('DISCOVERED'),
    name_phase VARCHAR(255) NOT NULL,
    
    CHECK(status_phase IN ('DISCOVERED', 'COMPLETED')),
    PRIMARY KEY(id_phase),
    FOREIGN KEY(id_world_progress) REFERENCES world_progress(id_world_progress)
);

CREATE TABLE equiped_item(
	id_equiped_item BIGINT AUTO_INCREMENT,
    id_slot BIGINT UNIQUE NOT NULL,
    armor VARCHAR(255) NOT NULL,
    sword VARCHAR(255) NOT NULL,
    
    PRIMARY KEY(id_equiped_item),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE inventory_item(
	id_inventory_item BIGINT AUTO_INCREMENT,
    id_slot BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT(1),
    type_item VARCHAR(255) NOT NULL DEFAULT('UTILITY'),
    name_item VARCHAR(255) NOT NULL,
    
    CHECK(quantity >= 1 AND quantity <= 10),
	CHECK(type_item IN ('SWORD', 'ARMOR', 'THROWABLE', 'EATABLE', 'UTILITY')),
    PRIMARY KEY(id_inventory_item),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot)
);

CREATE TABLE usable_item(
	id_usable_item BIGINT AUTO_INCREMENT,
	id_slot BIGINT NOT NULL,
    id_inventory_item BIGINT UNIQUE NOT NULL,
    number_item INT NOT NULL,
    
    CHECK(number_item >= 1 AND number_item <= 5),
	PRIMARY KEY(id_usable_item),
    FOREIGN KEY(id_slot) REFERENCES slot(id_slot),
    FOREIGN KEY(id_inventory_item) REFERENCES inventory_item(id_inventory_item)
);