/* CREATE DATABASE Shopaholic; */
USE Shopaholic;

CREATE TABLE Admin(
	AID CHAR(5) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    UserName VARCHAR(25) NOT NULL,
	UserPassword VARCHAR(25) NOT NULL
);

CREATE TABLE MemberUsers(
	UID CHAR(5) PRIMARY KEY NOT NULL,
    FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    ShippingAddress VARCHAR(50) DEFAULT '123 Post St. Los Angeles, CA 90008',    
    UserName VARCHAR(25) NOT NULL,
	UserPassword VARCHAR(25) NOT NULL,
    JoinDate DATE
);

CREATE TABLE GuestUsers(
	UID CHAR(5) PRIMARY KEY NOT NULL,
	FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    ShippingAddress VARCHAR(50) DEFAULT '123 Post St. Los Angeles, CA 90008'
);

CREATE TABLE MemberUsersFriends(
	UID CHAR(5) NOT NULL,
    FUID CHAR(5) NOT NULL,
    PRIMARY KEY(UID, FUID),
    FOREIGN KEY(UID) REFERENCES MemberUsers(UID) ON DELETE CASCADE
);

CREATE TABLE Merchants(
	MID CHAR(5) PRIMARY KEY NOT NULL,
	FirstName VARCHAR(25) NOT NULL,
    LastName VARCHAR(25) NOT NULL,
    UserName VARCHAR(25) NOT NULL,
	UserPassword VARCHAR(25) NOT NULL,
    JoinDate DATE
);

CREATE TABLE Products(
	PID CHAR(5) PRIMARY KEY NOT NULL, 
    ProductName VARCHAR(25) NOT NULL,
	Price INTEGER NOT NULL,
    ProductType VARCHAR(25),
    ProductDescription VARCHAR(50),
    Availability BOOLEAN,
    ProductStatus VARCHAR(25)
);

CREATE TABLE MerchantProducts(
	MID CHAR(5) NOT NULL,
    PID CHAR(5) NOT NULL,
    PRIMARY KEY(MID, PID),
    FOREIGN KEY(MID) REFERENCES Merchants(MID) ON DELETE CASCADE,
    FOREIGN KEY(PID) REFERENCES Products(PID) ON DELETE CASCADE
);

CREATE TABLE Orders(
	OID CHAR(5) PRIMARY KEY NOT NULL,
    UID CHAR(5) NOT NULL,
    OrderDate DATE,
    FOREIGN KEY(UID) REFERENCES MemberUsers(UID) ON DELETE CASCADE
);

CREATE TABLE MerchantOrders(
	MID CHAR(5) NOT NULL,
    OID CHAR(5) NOT NULL,
    PRIMARY KEY(MID, OID),
    FOREIGN KEY(MID) REFERENCES Merchants(MID) ON DELETE CASCADE,
    FOREIGN KEY(OID) REFERENCES Orders(OID) ON DELETE CASCADE
);

CREATE TABLE Reviews(
	RID CHAR(5) PRIMARY KEY NOT NULL,
    PID CHAR(5) NOT NULL, 
    Author VARCHAR(25) NOT NULL,
    ProductName VARCHAR(25) NOT NULL,
    ReviewDescription VARCHAR(50),
    Stars INTEGER,
    Likes INTEGER,
    ReviewDate DATE,
    FOREIGN KEY(PID) REFERENCES Products(PID) ON DELETE CASCADE
);

CREATE TABLE ReviewsTextualContext(
	RID CHAR(5) NOT NULL,
    ContextType VARCHAR(25) NOT NULL,
    PRIMARY KEY(RID, ContextType),
    FOREIGN KEY(RID) REFERENCES Reviews(RID) ON DELETE CASCADE
);

CREATE TABLE Cart(
	CID CHAR(5) PRIMARY KEY NOT NULL,
    UID CHAR(5) NOT NULL,
    Quantity INTEGER,
    TotalPrice INTEGER,
    FOREIGN KEY(UID) REFERENCES MemberUsers(UID) ON DELETE CASCADE
);

CREATE TABLE CartProducts(
	CID CHAR(5) NOT NULL,
	PID CHAR(5) NOT NULL,
    ProductName VARCHAR(25) NOT NULL,
    PRIMARY KEY(CID, PID),
    FOREIGN KEY(CID) REFERENCES Cart(CID) ON DELETE CASCADE,
    FOREIGN KEY(PID) REFERENCES Products(PID) ON DELETE CASCADE
);

/*****************************INSERTING ADMIN INFO******************************/
INSERT INTO Admin(AID, FirstName, LastName, UserName, UserPassword)
VALUES("AA000", "Admin", "Admin", "Admin", "pass");

/*****************************INSERTING MEMBER USER INFO******************************/
INSERT INTO MemberUsers(UID, FirstName, LastName, UserName, UserPassword)
VALUES("UU000", "UserOne", "1", "User1", "pass");

INSERT INTO MemberUsers(UID, FirstName, LastName, UserName, UserPassword)
VALUES("UU111", "UserTwo", "2", "User2", "pass");

INSERT INTO MemberUsers(UID, FirstName, LastName, UserName, UserPassword)
VALUES("UU222", "UserThree", "3", "User3", "pass");

INSERT INTO MemberUsers(UID, FirstName, LastName, UserName, UserPassword)
VALUES("UU333", "UserFour", "4", "User4", "pass");

/*****************************INSERTING MERCHANT INFO******************************/
INSERT INTO Merchants(MID, FirstName, LastName, UserName, UserPassword)
VALUES("MM000", "Merchant", "1", "Merchant1", "pass");

INSERT INTO Merchants(MID, FirstName, LastName, UserName, UserPassword)
VALUES("MM111", "Merchant", "2", "Merchant2", "pass");

INSERT INTO Merchants(MID, FirstName, LastName, UserName, UserPassword)
VALUES("MM222", "Merchant", "3", "Merchant3", "pass");

INSERT INTO Merchants(MID, FirstName, LastName, UserName, UserPassword)
VALUES("MM333", "Merchant", "4", "Merchant4", "pass");

/*****************************INSERTING PRODUCT INFO******************************/
INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP000", "T-Shirt", 7, "Clothing");

INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP111", "Dress", 24, "Clothing");

INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP222", "Table", 150, "Furniture");

INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP333", "Chair", 110, "Furniture");

INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP444", "Fruit Basket", 18, "Food");

INSERT INTO Products(PID, ProductName, Price, ProductType)
VALUES("PP555", "Bread", 12, "Food");

/*****************************INSERTING CART INFO******************************/
INSERT INTO Cart(CID, UID)
VALUES("CC000", "UU000");

INSERT INTO Cart(CID, UID)
VALUES("CC111", "UU111");

INSERT INTO Cart(CID, UID)
VALUES("CC222", "UU222");

INSERT INTO Cart(CID, UID)
VALUES("CC333", "UU333");

/*****************************INSERTING CART PRODUCT INFO******************************/
INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC000", "PP000", "T-Shirt");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC000", "PP111", "Dress");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC111", "PP222", "Table");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC111", "PP333", "Chair");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC222", "PP444", "Fruit Basket");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC222", "PP555", "Bread");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC333", "PP111", "Dress");

INSERT INTO CartProducts(CID, PID, ProductName)
VALUES("CC333", "PP444", "Fruit Basket");