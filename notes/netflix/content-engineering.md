Content Engineering Feature- Federated Graph Searchable

Federated GraphQL platform
    Enables domain team to build and operate independently on their own domain using Domain Graph Services
    Connect their domain with other domains in a unified GraphQL schema exposed by a federated gateway.

As an Example:

    Movie: At Netflix, we make titles (shows, films, shorts etc.). For simplicity, let’s assume each title is a Movie object.
    Production: Each Movie is associated with a Studio Production. 
    A Production object tracks everything needed to make a Movie including shooting location, vendors, and more.
    Talent: the people working on a Movie are the Talent, including actors, directors, and so on.

Schema

    Movie:
        Id: ID!
        title: string
        production: Production
        actor: [Talent]
    
    Production:
        productionId:ID!
        vendor: Vendor
    
    Talent:
        talentId: ID!
        name: string
        role: string

In a federated graph architecture, how can we answer such a query given that each entity is served by its own service? 

The Movie service would need to provide an endpoint that accepts a query and filters that may apply to data the service does not own, and use those to identify the appropriate Movie entities to return.

In fact, every entity owning service could be required to do this work.

This common problem of making a federated graph searchable led to the creation of Studio Search.

Studio Search







