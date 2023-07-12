'use strict';

const express = require('express');
const router = express.Router();
// const models = require('../models');


// 
// view the admin page
// 
router.get('/', function(req, res, next) {
  res.render('admin/index');
  });
  








module.exports = router;