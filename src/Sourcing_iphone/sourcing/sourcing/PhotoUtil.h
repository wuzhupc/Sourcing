//
//  PhotoUtil.h
//  Fellow
//
//  Created by wuzhu on 12-11-23.
//
//

#import <Foundation/Foundation.h>
#import "MWPhotoBrowser.h"

@interface PhotoUtil : NSObject <MWPhotoBrowserDelegate>
{
    NSArray *_photos;
}
@property NSArray *photos;

- (id)initWithImageUrl:(NSString *)imgurl;
-(void)showPhotoBrowser:(UIViewController *)kvc;
@end
