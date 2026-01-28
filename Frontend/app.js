// ================================
// BASE URL (Spring Boot Backend)
// ================================
const BASE_URL = "http://localhost:8080/api";

// ================================
// REGISTER USER
// POST /api/auth/register
// ================================
function registerUser() {
  const data = {
    name: document.getElementById("name").value,
    email: document.getElementById("email").value,
    password: document.getElementById("password").value,
    role: document.getElementById("role").value
  };

  fetch(`${BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
  .then(res => res.text())
  .then(msg => {
    alert(msg);
    window.location.href = "login.html";
  })
  .catch(err => alert("Register failed"));
}


// ================================
// LOGIN USER
// POST /api/auth/login
// ================================
function loginUser() {
  const data = {
    email: document.getElementById("email").value,
    password: document.getElementById("password").value
  };

  fetch(`${BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
  .then(res => res.json())
  .then(user => {
    localStorage.setItem("user", JSON.stringify(user));
    window.location.href = "dashboard.html";
  })
  .catch(err => alert("Invalid login"));
}


// ================================
// ADD BLOOD (Hospital Only)
// POST /api/blood/add/{hospitalId}
// ================================
function addBlood() {
  const user = JSON.parse(localStorage.getItem("user"));

  if (!user || user.role !== "HOSPITAL") {
    alert("Only hospital staff can add blood");
    return;
  }

  const data = {
    bloodGroup: document.getElementById("group").value,
    units: document.getElementById("units").value,
    hospitalName: document.getElementById("hospital").value,
    location: document.getElementById("location").value,
    contact: document.getElementById("contact").value
  };

  fetch(`${BASE_URL}/blood/add/${user.id}`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
  .then(res => res.text())
  .then(msg => alert(msg))
  .catch(err => alert("Error adding blood"));
}


// ================================
// SEARCH BLOOD (Anyone)
// GET /api/blood/search/{group}
// ================================
function searchBlood(){
  const group = document.getElementById("group").value;
  fetch(`http://localhost:8080/api/blood/search/${group}`)
    .then(res => res.json())
    .then(data => {
      const results = document.getElementById("results");
      results.innerHTML = "";

      if(data.length === 0){
        results.innerHTML = "<p style='color:white'>No blood available</p>";
        return;
      }

      data.forEach(b => {
        results.innerHTML += `
          <div class="result-card">
            <h3>🩸 ${b.bloodGroup}</h3>
            <p><b>Hospital:</b> ${b.hospitalName}</p>
            <p><b>Location:</b> ${b.location}</p>
            <p><b>Units:</b> ${b.units}</p>
            <p><b>Contact:</b> ${b.contact}</p>

            <button class="delete-btn"
              onclick="deleteBlood(${b.id})">
              Delete
            </button>
          </div>
        `;
      });
    });
}


// ================================
// DELETE BLOOD (Hospital Only)
// DELETE /api/blood/delete/{hospitalId}/{stockId}
// ================================
function deleteBlood(id){
  if(!confirm("Delete this blood stock?")) return;

  fetch(`http://localhost:8080/api/blood/delete/${id}`,{
    method:"DELETE"
  })
  .then(() => {
    alert("Blood stock deleted");
    searchBlood();
  });
}
