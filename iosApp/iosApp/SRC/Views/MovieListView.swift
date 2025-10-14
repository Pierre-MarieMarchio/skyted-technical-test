import SwiftUI
import ComposeApp

struct MovieListView: View {
    @StateObject private var viewModel: MovieListViewModelWrapperIos
    @State private var searchText = ""

    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    let createMovieDetailsVM: () -> MovieDetailsVM

    init(movieListVM: MovieListVM, createMovieDetailsVM: @escaping () -> MovieDetailsVM) {
        _viewModel = StateObject(wrappedValue: MovieListViewModelWrapperIos(vm: movieListVM))
        self.createMovieDetailsVM = createMovieDetailsVM
    }

    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                TextField("Search movies...", text: $searchText)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal)

                Button("Search") {
                    viewModel.search(query: searchText)
                }
                .padding(.horizontal)
                .buttonStyle(.borderedProminent)

                if viewModel.isLoading {
                    ProgressView("Loading...")
                        .padding()
                } else if let error = viewModel.errorMessage {
                    Text("Error: \(error)")
                        .foregroundColor(.red)
                        .padding()
                } else if viewModel.movies.isEmpty {
                    Text("No movies found. Enter a query and press Search.")
                        .foregroundColor(.gray)
                        .padding()
                } else {
                    ScrollView {
                        LazyVGrid(columns: columns, spacing: 16) {
                            ForEach(viewModel.movies, id: \.id) { movie in
                                NavigationLink(
                                    destination: MovieDetailsView(
                                        movieDetailsVM: createMovieDetailsVM(),
                                        movieId: movie.id
                                    )
                                ) {
                                    MovieGridItem(movie: movie)
                                }
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            }
            .navigationTitle("Movies")
        }
    }
}

struct MovieGridItem: View {
    let movie: MovieListItemDto

    var body: some View {
        VStack(alignment: .leading) {
            AsyncImage(url: URL(string: movie.image ?? "")) { phase in
                switch phase {
                case .empty:

                    ZStack {
                        Color.gray.opacity(0.3)
                        ProgressView()
                    }
                    .frame(height: 200)
                    .cornerRadius(10)

                case .success(let image):
                    image
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(height: 200)
                        .clipped()
                        .cornerRadius(10)

                case .failure(_):

                    ZStack {
                        Color.red.opacity(0.3)
                        Image(systemName: "film")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 40, height: 40)
                            .foregroundColor(.white)
                    }
                    .frame(height: 200)
                    .cornerRadius(10)

                @unknown default:
                    EmptyView()
                }
            }

            Text(movie.title)
                .font(.headline)
                .lineLimit(1)
                .padding(.top, 4)
        }
    }
}