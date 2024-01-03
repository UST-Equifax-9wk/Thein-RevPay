export interface User {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
}

export interface Credential {
  username: string;
  password: string;
}

export interface Registration {
  credential: Credential;
  user: User;
}

export interface Principal {
  username: string;
  id: string;
  email: string;
}

export interface UserReponse {
  id: string;
  firstName: string;
  lastName: string;
  username: string;
  email: string;
}

export interface AdminResponse {
  username: string;
  loans: LoanResponse[];
}

export interface Card {
  nameOnCard: string;
  cardNumber: string;
  svc: string;
  expirationDate: string;
  bankAccount: {
    id: string;
  };
}

export interface CardResponse {
  id: string;
  nameOnCard: string;
  cardNumber: string;
  svc: string;
  expirationDate: string;
}

export interface BankAccountRequestBody {
  accountNumber: string;
  routingNumber: string;
  type: string;
  balance: string;
  accountHolderId: string;
}

export interface LoanResponse {
  id: string;
  amount: string;
  status: string;
}

export interface BankAccount {
  id: string;
  accountNumber: string;
  routingNumber: string;
  type: string;
  balance: number;
}

export interface EmailDto {
  to: string;
  subject: string;
  text: string;
}

export interface Transaction {
  id: string;
  description: string;
  amount: number;
  createdDate: string;
  status: string;
}

export interface UserForm {
  firstName: string;
  lastName: string;
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
}

export interface BankAccoutDetail {
  accountNumber: string;
  routingNumber: string;
  type: string;
  balance: number;
  incomingTransactions: Transaction[];
  outgoingTransactions: Transaction[];
  cards: CardResponse[];
  loans: LoanResponse[];
}
