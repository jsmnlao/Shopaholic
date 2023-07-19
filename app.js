require('dotenv').config();

const express = require('express');
const path = require('path');
const i18n = require('i18n');
const flash = require('connect-flash');
const expressLayouts = require('express-ejs-layouts');
const bodyParser = require('body-parser')
const indexRouter = require('./routes/index');
const adminRouter = require('./routes/admin');
const merchantRouter = require('./routes/merchant');
const userRouter = require('./routes/users');
const db = require('./models');

const app = express();


app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
app.use(expressLayouts);
app.use(flash());
app.use(bodyParser.urlencoded({ extended: false }))

// parse application/json
app.use(bodyParser.json())


/// load in all the configured routes in /routes/index.js
i18n.configure({
  locales: ['en'],
  directory: path.join(__dirname, 'locales'),
});
app.use(i18n.init);
/// set up local variables commonly used in all requests
app.use((req, res, next) => {
  /// set the current logged in user, if any
  res.locals.currentUser = req.user;
  next();
});

db.sequelize.sync({ force: false })

app.use('/', indexRouter);
app.use('/admin', adminRouter);
app.use('/merchant', merchantRouter);
app.use('/user', userRouter);


module.exports = app;
