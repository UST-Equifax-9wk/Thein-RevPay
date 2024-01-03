--credentials table
insert into credentials (username, password) values ('thein', 'a');
insert into credentials (username, password) values ('hein', 'a');

--users table
insert into users (username, first_name, last_name, email) values ('thein', 'Thein', 'Hein', 'th.hein04@gmail.com')
insert into users (username, first_name, last_name, email) values ('john', 'John', 'Doe', 'john.doe@gmail.com')
insert into users (username, first_name, last_name, email) values ('hein', 'Thein Than', 'Hein', 'thein.than.hein.th@gmail.com')

--bank_accounts table
insert into bank_accounts (account_holder_id, account_number, routing_number, balance, type) values (1, '123456789', '234567890', 5000.00, 'checking')
insert into bank_accounts (account_holder_id, account_number, routing_number, balance, type) values (1, '123456789', '234567890', 45000.00, 'saving')
insert into bank_accounts (account_holder_id, account_number, routing_number, balance, type) values (1, '238483823', '234567890', 15000.00, 'business')
insert into bank_accounts (account_holder_id, account_number, routing_number, balance, type) values (2, '238483823', '234567890', 15000.00, 'business')
insert into bank_accounts (account_holder_id, account_number, routing_number, balance, type) values (3, '234324324', '234343243', 25000.00, 'checking')
--transactions table
insert into transactions (amount, sender_account_id, receiver_account_id, created_date, status, description) values (500.00, 1, 2, '2024-03-03 05:04:24', 'pending', 'Walmart')

--admins table
insert into admins (username, password) values ('th', 'th')

--cards table
insert into cards (name_on_card, card_number, svc, expiration_date, bank_account_id) values ('Thein Hein', '1234567', '123', '2025-12-09', 1)