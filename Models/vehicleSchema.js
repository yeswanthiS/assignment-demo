const mongoose = require('mongoose')
const { type } = require('os')

const vehicleSchema= new mongoose.Schema({
    model:{
        type:String,
    },
    year:{
        type:String,
    },
    photo:{
       type:String,
    }
});
module.exports = vehicleSchema;