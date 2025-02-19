import React from "react";
import Stripe from "react-stripe-checkout";
import axios from "axios";
import { getCharge } from "../Api/Paymentsapi";
function StripePayment() {
  async function handleToken(token) {
    console.log(token);
    const body = {
      token,
      amount: 500,
    };
    try {
      await getCharge(body);
      alert("Payment Success");
    } catch (error) {
      alert("Payment Failed: " + error.message);
    }
  }

  return (
    <div className="App">
      <Stripe
        stripeKey="pk_test_51QtkA9QkHXfzIVwa63UmPWeG6DdgLsb0VD9koA2qNa0ohs0hI0qIECEKYIaoo2emu2TNrZWZTAqdXxsTqdJd43a500qBcgO0us"
        token={handleToken}
      />
    </div>
  );
}
export default StripePayment;
