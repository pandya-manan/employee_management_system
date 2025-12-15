import { useEffect, useState } from "react";
import { createEmployee, updateEmployee } from "../services/EmployeeService";

function EmployeeForm({ selectedEmployee, onSuccess, onCancel }) {
  const [employee, setEmployee] = useState({
    firstName: "",
    lastName: "",
    emailId: "",
  });

  const isEdit = Boolean(selectedEmployee);

  useEffect(() => {
    if (selectedEmployee) {
      setEmployee({
        firstName: selectedEmployee.firstName,
        lastName: selectedEmployee.lastName,
        emailId: selectedEmployee.emailId,
      });
    }
  }, [selectedEmployee]);

  const handleChange = (e) => {
    setEmployee({
      ...employee,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      if (isEdit) {
        await updateEmployee(selectedEmployee.id, employee);
      } else {
        await createEmployee(employee);
      }

      setEmployee({ firstName: "", lastName: "", emailId: "" });
      onSuccess();
    } catch (err) {
      alert("Operation failed");
    }
  };

  return (
    <div className="mb-8 p-6 border rounded-lg bg-white shadow">
      <h3 className="text-xl font-semibold mb-4">
        {isEdit ? "Update Employee" : "Add Employee"}
      </h3>

      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          name="firstName"
          placeholder="First Name"
          value={employee.firstName}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded"
        />

        <input
          type="text"
          name="lastName"
          placeholder="Last Name"
          value={employee.lastName}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded"
        />

        <input
          type="email"
          name="emailId"
          placeholder="Email"
          value={employee.emailId}
          onChange={handleChange}
          className="w-full border px-4 py-2 rounded"
        />

        <div className="flex gap-3">
          <button
            type="submit"
            className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
          >
            {isEdit ? "Update" : "Add"}
          </button>

          {isEdit && (
            <button
              type="button"
              onClick={onCancel}
              className="bg-gray-400 text-white px-6 py-2 rounded"
            >
              Cancel
            </button>
          )}
        </div>
      </form>
    </div>
  );
}

export default EmployeeForm;
