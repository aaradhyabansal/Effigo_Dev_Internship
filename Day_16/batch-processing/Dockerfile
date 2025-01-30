FROM node:18 AS build


WORKDIR /app

COPY package.json package-lock.json* ./
RUN npm install


COPY . .


RUN npm run build


FROM node:18


WORKDIR /app


COPY --from=build /app/dist ./dist


COPY package.json package-lock.json* ./


RUN npm install


EXPOSE 4173


CMD ["npx", "vite", "preview", "--host"]

