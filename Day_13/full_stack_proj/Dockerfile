# Stage 1: Build the React app
FROM node:18 AS build

WORKDIR /app

# Copy package.json and package-lock.json for dependency installation
COPY package.json package-lock.json* yarn.lock* ./

RUN npm install

# Copy the rest of the app's source code
COPY . .

# Build the app (Vite builds to 'dist' by default)
RUN npm run build

# Stage 2: Serve with Nginx
FROM nginx:1.23-alpine

# Copy build artifacts from the first stage
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
