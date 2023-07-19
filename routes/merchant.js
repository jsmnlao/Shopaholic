// 'use strict';

const express = require('express');
const router = express.Router();
const models = require('../models');
const merchant = require('../models/merchant');


// 
// view all merchants
// 
router.get('/', function(req, res, next) {
  models.merchant.findAll().then(function(records){
    res.render('merchant/index', {
    records: records
    });
  });
});
  


// 
//  get the new merchant site
// 
router.get('/new', function(req, res, next) {
  res.render('merchant/new');
});


// 
// post request for new merchant
// 
router.post('/', async (req, res) => {
  try {
    models.merchant.create({name:req.body.merch}).then(function(record){
      res.redirect('/merchant');
    })
  } catch (error) {
    res.status(500).send('Error creating a new yourModel');
  }
});



// 
// Show a single merchant
// 
router.get('/:id/', function(req, res, next){
  models.merchant.findByPk(req.params.id).then(function(merchant){
      res.render('merchant/merchant', {
        merchant: merchant,
      });
    });
});




module.exports = router;