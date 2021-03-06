/**
 * http://usejsdoc.org/
 */
var express = require('express');
var router = express.Router();

// middleware that is specific to this router
router.use(function(req, res, next) {
  console.log('Time: ', Date.now());
  next();
});

// define the home page route
router.get('/', function(req, res) {
  res.send('Cows home page');
});

// define the about route
router.get('/about', function(req, res) {
  res.send('About cows');
});

module.exports = router;