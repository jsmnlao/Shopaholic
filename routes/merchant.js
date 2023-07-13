'use strict';

const express = require('express');
const router = express.Router();
const models = require('../models');


// 
// view merchants
// 
router.get('/', function(req, res, next) {
  models.merchant.findAll().then(function(records){
    res.render('merchant/index', {
    records: records
    });
  });
});
  


router.get('/new', function(req, res, next) {
  res.render('merchant/new');
});



router.post('/', async (req, res) => {
  try {
    // const { name } = req.body;
    await models.merchant.create({
      name: req.body.name
    })
    res.json(yourModel);
  } catch (error) {
    res.status(500).send('Error creating a new yourModel');
  }
});





module.exports = router;