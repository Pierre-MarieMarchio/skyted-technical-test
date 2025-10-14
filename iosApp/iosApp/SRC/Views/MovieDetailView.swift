import SwiftUI
import ComposeApp

struct MovieDetailsView: View {
    @StateObject private var viewModelWrapper: MovieDetailsViewModelWrapperIos
    let movieId: String

    init(movieDetailsVM: MovieDetailsVM, movieId: String) {
        _viewModelWrapper = StateObject(wrappedValue: MovieDetailsViewModelWrapperIos(vm: movieDetailsVM))
        self.movieId = movieId
    }

    var body: some View {
        VStack {
            if viewModelWrapper.isLoading {
                ProgressView("Loading movie...")
                    .padding()
            } else if let error = viewModelWrapper.errorMessage {
                Text("Error: \(error)")
                    .foregroundColor(.red)
                    .padding()
            } else if let movie = viewModelWrapper.movie {
                ScrollView {
                    VStack(alignment: .leading, spacing: 16) {
                        if let imageUrl = movie.image, let url = URL(string: imageUrl) {
                            AsyncImage(url: url) { phase in
                                switch phase {
                                case .empty:
                                    ProgressView()
                                        .frame(height: 250)
                                case .success(let image):
                                    image
                                        .resizable()
                                        .aspectRatio(contentMode: .fit)
                                        .frame(maxWidth: .infinity)
                                        .cornerRadius(12)
                                case .failure(_):
                                    Image(systemName: "film")
                                        .resizable()
                                        .scaledToFit()
                                        .frame(height: 250)
                                        .foregroundColor(.gray)
                                @unknown default:
                                    EmptyView()
                                }
                            }
                        }

                        Text(movie.title)
                            .font(.title)
                            .bold()


                        Spacer()
                    }
                    .padding()
                }
            } else {
                Text("No movie details available.")
                    .foregroundColor(.gray)
                    .padding()
            }
        }
        .navigationTitle(viewModelWrapper.movie?.title ?? "Details")
        .navigationBarTitleDisplayMode(.inline)
        .onAppear {
            viewModelWrapper.loadMovie(id: movieId)
        }
    }
}