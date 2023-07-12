'use strict';

const express = require('express');
const router = express.Router();
// const models = require('../models');


// 
// view merchants
// 
router.get('/', function(req, res, next) {
  res.render('merchant/index');
});
  





module.exports = router;