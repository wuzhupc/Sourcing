//
//  PhotoUtil.m
//  Fellow
//
//  Created by wuzhu on 12-11-23.
//
//

#import "PhotoUtil.h"
#import "MWPhoto.h"
#import "HtmlUtil.h"

@implementation PhotoUtil
@synthesize photos = _photos;

- (id)initWithImageUrl:(NSString *)imgurl
{
    if ((self = [self init]))
    {
        if ([HtmlUtil isImageUrl:imgurl]) {
            self.photos = [[NSMutableArray alloc] initWithObjects:[MWPhoto photoWithURL:[NSURL URLWithString:imgurl]],nil];
        }
    }
    return self;
}


#pragma mark - MWPhotoBrowserDelegate

- (NSUInteger)numberOfPhotosInPhotoBrowser:(MWPhotoBrowser *)photoBrowser
{
    if(_photos==nil)
        return 0;
    return _photos.count;
}

- (MWPhoto *)photoBrowser:(MWPhotoBrowser *)photoBrowser photoAtIndex:(NSUInteger)index {
    if (_photos!=nil&&index < _photos.count)
        return [_photos objectAtIndex:index];
    return nil;
}

-(void)showPhotoBrowser:(UIViewController *)kvc
{
    MWPhotoBrowser *browser = [[MWPhotoBrowser alloc] initWithDelegate:self];
    browser.displayActionButton = YES;
    UINavigationController *nc = [[UINavigationController alloc] initWithRootViewController:browser];
    nc.modalTransitionStyle = UIModalTransitionStyleCrossDissolve;
    [kvc presentModalViewController:nc animated:YES];
}

@end
