CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create table devices(
    id  uuid    PRIMARY KEY DEFAULT uuid_generate_v4(),
    name varchar not null,
    technology varchar not null,
    two_g  varchar null,
    three_g  varchar null,
    four_g  varchar null
);

create table booking_history(
    id  uuid    PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id int not null,
    updated timestamp not  null,
    status varchar not null ,
    device_id uuid references devices(id) not null
);