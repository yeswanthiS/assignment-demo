const bodyparser = require('body-parser');
const express = require('express');
const{ fileClaim, trackStatus, vehicledetails} = require('./Models/user');
const bodyParser = require('body-parser');
const app = express()
const PORT = process.env.PORT || 3000;
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended:true}))

app.post('/claim', async(req, res)=>{
    const body = req.body;

    const claim = await fileClaim.create({
        holdername: body.holdername,
        date:body.date,
        mobilenumber:body.mobilenumbe,
        policyreport:body.policyreport,
        place:body.place,
        damagedetails:body.damagedetails,
    });
    claim ? res.status(201).json({ msg: "Success", data: claim }) : res.status(500).json({ msg: "Error" });
})
app.get("/",(req,res)=>{
    res.end("Hello world")
})
app.post('/status', async(req,res)=>{
    const body= req.body;
    const status=await trackStatus.create({
        claimnumber:body.claimnumber,
        date:body.date,
        Status:body.Status,
    });
    status ? res.status(201).json({ msg: "Success", data: status }) : res.status(500).json({ msg: "Error" });
})
app.post('/vehicle',async(req,res)=>{
    const body=req.body;

    const vehicle = await vehicledetails.create({
        model:body.model,
        year:body.year,
        photo:body.photo,
    });
    vehicle ? res.status(201).json({ msg: "Success", data: vehicle }) : res.status(500).json({ msg: "Error" });
})

app.post("/login",(req,res)=>{
    const body = req.body;
    const username = body.username;
    const pass= body.pass;

    if(email ==="yeswanthi05g7@gmail.com" && pass===yessi)
        res.json({
            data:"success"
    })
    else
       res.end("Incorrect credentials")
})
app.listen(PORT, ()=>console.log(`Application listening on port ${PORT}`));