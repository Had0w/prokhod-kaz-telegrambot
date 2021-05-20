CREATE TABLE users (
chatId integer PRIMARY KEY,
timeOfStartWorkDay time with time zone DEFAULT '8:00',
timeOfLunch time with time zone DEFAULT '12:00',
timeOfEndWorkDay time with time zone DEFAULT '17:00',
isAtWork boolean DEFAULT FALSE,
coeff numeric(1, 1) DEFAULT 0,
role varchar(20) DEFAULT 'USER',
lastUpdate timestamp with time zone
);