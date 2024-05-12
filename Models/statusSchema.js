const mongoose = require('mongoose');
const statusSchema = new mongoose.Schema({
    claimnumber:{
        type:Number,
        required:true,
        unique:true,
    },
    date:{
        type:Date,
    },
    status:{
        type:String,
    }
});
module.exports = statusSchema;