import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./AuthContext";
import {
  addTodoForUser,
  retrieveTodoForUserById,
  updateTodoForUserById,
} from "../api/TodoApiService";
import { useEffect, useState } from "react";
import { Formik, Form, Field, ErrorMessage } from "formik";

function TodoComponent() {
  const { id } = useParams();
  const authContext = useAuth();
  const username = authContext.username;
  const [description, setDescription] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const navigate = useNavigate();

  useEffect(() => retrieveTodo(), [id]);

  function retrieveTodo() {
    if (id != -1) {
      retrieveTodoForUserById(username, id)
        .then((response) => {
          setDescription(response.data.description);
          setTargetDate(response.data.targetDate);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    }
  }
  function onSubmit(values) {
    const todo = {
      id,
      username,
      description: values.description,
      targetDate: values.targetDate,
      done: false,
    };
    if (id == -1) {
      addTodoForUser(username, todo)
        .then((response) => {
          navigate(`/listtodos/${username}`);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    } else {
      updateTodoForUserById(username, id, todo)
        .then((response) => {
          navigate(`/listtodos/${id}`);
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    }
    console.log(todo);
  }
  function validate(values) {
    let errors = {};
    if (values.description.length < 5) {
      errors.description = "Enter atleast 5 characters";
    }
    if (values.targetDate == null || values.targetDate == "") {
      errors.targetDate = "Enter a valid target date";
    }
    console.log(values);
    return errors;
  }
  return (
    <div className="container">
      <h1>Enter Todo Details</h1>
      <div>
        <Formik
          initialValues={{ description, targetDate }}
          enableReinitialize={true}
          onSubmit={onSubmit}
          validate={validate}
          validateOnChange={false}
          validateOnBlur={false}
        >
          {(props) => (
            <Form>
              <ErrorMessage
                name="description"
                component="div"
                className="alert alert-warning"
              />
              <ErrorMessage
                name="targetDate"
                component="div"
                className="alert alert-warning"
              />
              <fieldset className="form-group">
                <label> Description:</label>
                <Field
                  type="text"
                  className="form-control"
                  name="description"
                />
              </fieldset>
              <fieldset className="form-group">
                <label> Target Date:</label>
                <Field type="date" className="form-control" name="targetDate" />
              </fieldset>
              <div>
                <button type="submit" className="btn btn-success">
                  Update
                </button>
              </div>
            </Form>
          )}
        </Formik>
      </div>
    </div>
  );
}

export default TodoComponent;
