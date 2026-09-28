import { useEffect, useState } from "react";
import api from "../api";

export default function AdminPage() {

  const empty = {
    flightNumber:"",
    airline:"",
    source:"",
    destination:"",
    flightDate:"",
    departureTime:"",
    arrivalTime:"",
    price:"",
    totalSeats:""
  };

  const [flights,setFlights]=useState([]);
  const [form,setForm]=useState(empty);
  const [editId,setEditId]=useState(null);

  const loadFlights = async ()=>{
    const res = await api.get("/api/flights");
    setFlights(res.data);
  };

  useEffect(()=>{
    loadFlights();
  },[]);

  const saveFlight = async ()=>{

    const payload={
      ...form,
      price:Number(form.price),
      totalSeats:Number(form.totalSeats)
    };

    if(editId){
      await api.put(`/api/flights/${editId}`,payload);
      alert("Flight Updated");
    }else{
      await api.post("/api/flights",payload);
      alert("Flight Added");
    }

    setForm(empty);
    setEditId(null);
    loadFlights();
  };

  const editFlight=(f)=>{
    setEditId(f.id);

    setForm({
      flightNumber:f.flightNumber,
      airline:f.airline,
      source:f.source,
      destination:f.destination,
      flightDate:f.flightDate,
      departureTime:f.departureTime.substring(0,5),
      arrivalTime:f.arrivalTime.substring(0,5),
      price:f.price,
      totalSeats:f.totalSeats
    });
  };

  const deleteFlight=async(id)=>{
    if(!window.confirm("Delete Flight?")) return;
    await api.delete(`/api/flights/${id}`);
    loadFlights();
  };

  return(
    <div style={{padding:30}}>
      <h1>✈ SkyBook Admin Dashboard</h1>

      <h3>{editId ? "Edit Flight":"Add Flight"}</h3>

      {Object.keys(form).map(key=>(
        <div key={key}>
          <input
            style={{padding:8,width:260,margin:4}}
            placeholder={key}
            value={form[key]}
            onChange={(e)=>setForm({...form,[key]:e.target.value})}
          />
        </div>
      ))}

      <button onClick={saveFlight}>
        {editId ? "Update Flight":"Add Flight"}
      </button>

      <hr/>

      <h2>Flights</h2>

      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Flight</th>
            <th>Route</th>
            <th>Price</th>
            <th>Action</th>
          </tr>
        </thead>

        <tbody>
          {flights.map(f=>(
            <tr key={f.id}>
              <td>{f.id}</td>
              <td>{f.flightNumber}</td>
              <td>{f.source} → {f.destination}</td>
              <td>₹{f.price}</td>
              <td>
                <button onClick={()=>editFlight(f)}>Edit</button>
                {" "}
                <button onClick={()=>deleteFlight(f.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>

      </table>

    </div>
  );
}
