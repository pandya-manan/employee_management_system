import { useEffect, useState } from "react";
import EmployeeForm from "../components/EmployeeForm";
import { getAllEmployees, deleteEmployee } from "../services/EmployeeService";

function EmployeeList() {
  const [employees, setEmployees] = useState([]);
  const [selectedEmployee, setSelectedEmployee] = useState(null);

  const fetchEmployees = async () => {
    try {
      const response = await getAllEmployees();
      setEmployees(response.data);
    } catch (error) {
      console.error("Error fetching employees", error);
    }
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this employee?"
    );

    if (!confirmDelete) return;

    try {
      await deleteEmployee(id);
      fetchEmployees();
    } catch (error) {
      console.error("Error deleting employee", error);
      alert("Failed to delete employee");
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-5xl mx-auto">
        <h1 className="text-3xl font-bold mb-6 text-center">
          Employee Management
        </h1>

        <EmployeeForm
          selectedEmployee={selectedEmployee}
          onSuccess={() => {
            fetchEmployees();
            setSelectedEmployee(null);
          }}
          onCancel={() => setSelectedEmployee(null)}
        />

        <div className="bg-white shadow rounded-lg overflow-hidden">
          <table className="min-w-full border-collapse">
            <thead className="bg-gray-200">
              <tr>
                <th className="px-4 py-3 text-left">ID</th>
                <th className="px-4 py-3 text-left">First Name</th>
                <th className="px-4 py-3 text-left">Last Name</th>
                <th className="px-4 py-3 text-left">Email</th>
                <th className="px-4 py-3 text-left">Actions</th>
              </tr>
            </thead>
            <tbody>
              {employees.length === 0 ? (
                <tr>
                  <td colSpan="5" className="text-center py-6 text-gray-500">
                    No employees found
                  </td>
                </tr>
              ) : (
                employees.map((emp) => (
                  <tr key={emp.id} className="border-t hover:bg-gray-50">
                    <td className="px-4 py-3">{emp.id}</td>
                    <td className="px-4 py-3">{emp.firstName}</td>
                    <td className="px-4 py-3">{emp.lastName}</td>
                    <td className="px-4 py-3">{emp.emailId}</td>
                    <td className="px-4 py-3 space-x-2">
                      <button
                        onClick={() => setSelectedEmployee(emp)}
                        className="bg-yellow-500 hover:bg-yellow-600 text-white px-3 py-1 rounded"
                      >
                        Edit
                      </button>

                      <button
                        onClick={() => handleDelete(emp.id)}
                        className="bg-red-500 hover:bg-red-600 text-white px-3 py-1 rounded"
                      >
                        Delete
                      </button>
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default EmployeeList;
