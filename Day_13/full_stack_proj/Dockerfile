# Stage 1: Build the React app using Vite
FROM node:18 AS build

# Set the working directory
WORKDIR /app

# Copy package files and install dependencies
COPY package.json package-lock.json* ./
RUN npm install

# Copy the source code
COPY . .

# Build the Vite application
RUN npm run build

# Stage 2: Serve the React app using Vite's preview server
FROM node:18

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/dist ./dist

# Copy package.json and package-lock.json
COPY package.json package-lock.json* ./

# Install all dependencies (including dev dependencies to include Vite)
RUN npm install

# Expose the port for Vite's preview server (default: 4173)
EXPOSE 4173

# Start the Vite preview server
CMD ["npx", "vite", "preview", "--host"]

