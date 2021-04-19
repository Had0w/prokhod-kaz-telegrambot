CREATE TABLE users (
chatId integer PRIMARY KEY,
timeOfStartWorkDay time with time zone DEFAULT '8:00',
timeOfLunch time with time zone DEFAULT '12:00',
timeOfEndWorkDay time with time zone DEFAULT '17:00',
isAtWork boolean DEFAULT FALSE,
role varchar(20) DEFAULT 'USER',
lastUpdate timestamp with time zone
);

INSERT INTO users (chatId, timeOfStartWorkDay, timeOfEndWorkDay, role) VALUES (786831575, '7:30', '16:30', 'ADMIN');