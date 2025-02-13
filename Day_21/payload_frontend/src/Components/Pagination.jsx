import React from "react";
import styled from "styled-components";

const PaginationContainer = styled.div`
  display: flex;
  justify-content: center;
  gap: 8px;
  margin-top: 20px;
`;

const PageButton = styled.button`
  padding: 10px 16px;
  background-color: #e5989b;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease-in-out, box-shadow 0.3s ease-in-out,
    transform 0.3s ease-in-out;

  &:hover {
    background-color: #b5838d;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
    transform: translateY(-3px);
  }

  &.active {
    background-color: #b5838d;
  }
`;

const Pagination = ({
  totalPayments,
  paymentsPerPage,
  currentPage,
  setCurrentPage,
}) => {
  let pages = [];
  for (let i = 1; i <= Math.ceil(totalPayments / paymentsPerPage); i++) {
    pages.push(i);
  }

  return (
    <PaginationContainer>
      {pages.map((page) => (
        <PageButton
          key={page}
          className={currentPage === page ? "active" : ""}
          onClick={() => setCurrentPage(page)}
        >
          {page}
        </PageButton>
      ))}
    </PaginationContainer>
  );
};

export default Pagination;
